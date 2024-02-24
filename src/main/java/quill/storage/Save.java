package quill.storage;

import quill.exception.QuillException;
import quill.task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    private static void createNewFile() {
        File file = new File("./data/quill.txt");
        File directory = new File(file.getParent());
        try {
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + directory.getAbsolutePath());
                }
            }
            if (file.createNewFile()) {
                System.out.println("New file created: " + file.getAbsolutePath());
            } else {
                System.out.println("Failed to create new file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }
    }

    public static TaskList readFile() {
        TaskList tasks = new TaskList();
        File file = new File("./data/quill.txt");
        if (!file.exists()) {
            createNewFile();
            return tasks;
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();

                Task task = getTask(line);
                tasks.addTask(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        } catch (QuillException e) {
            System.out.println("QuillException while reading file");
        }
        return tasks;
    }

    private static Task getTask(String line) throws QuillException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new QuillException();
        }
        String taskType = parts[0].trim();
        boolean isDone = Boolean.parseBoolean(parts[1].trim());
        String taskDetails = parts[2].trim();
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(taskDetails);
            break;
        case "D":
            task = new Deadline(taskDetails);
            break;
        case "E":
            task = new Event(taskDetails);
            break;
        default:
            task = new Task("Error");
            break;
        }
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        return task;
    }

    public static void writeToFile(TaskList tasks) {
        try {
            PrintWriter fw = new PrintWriter("./data/quill.txt");
            for (int i = 0; i < tasks.getTotalTasks(); i++) {
                fw.println(tasks.getTask(i).saveTask());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }

    public static void appendToFIle(Task task) {
        try {
            FileWriter fw = new FileWriter("./data/quill.txt", true); // create a FileWriter in append mode
            fw.write(task.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error appending file" + e.getMessage());
        }
    }
}
