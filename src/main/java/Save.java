import quill.exception.QuillException;
import quill.task.Deadline;
import quill.task.Event;
import quill.task.Task;
import quill.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    private static void createNewFile() {
        File file = new File("data/quill.txt");
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

    public static ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("data/quill.txt");
        if (!file.exists()) {
            createNewFile();
            return tasks;
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();

                Task task = getTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        } catch (QuillException e) {
            System.out.println("QuillException while reading file");
        }
        return tasks;
    }

    private static Task getTask(String line) throws QuillException {
        String taskType = line.substring(1, 2);
        boolean isDone = Integer.parseInt(line.substring(4, 5)) == 1;
        String taskDetails = line.substring(9);
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

    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("data/quill.txt");
            for (Task task: tasks) {
                fw.write(task.saveTask());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }

    public static void appendToFIle(Task task) {
        try {
            FileWriter fw = new FileWriter("data/quill.txt", true); // create a FileWriter in append mode
            fw.write(task.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error appending file" + e.getMessage());
        }
    }
}
