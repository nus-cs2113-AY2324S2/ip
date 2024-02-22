package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class DataFile {
    protected static final String FILE_PATH = "data/ava.txt";

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        if (!file.exists()) {
            createFile();
        } else {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }
    }

    private static void createFile() {
        try {
            File dataFile = new File(FILE_PATH);
            if (!dataFile.getParentFile().exists()) {
                Files.createDirectories(dataFile.getParentFile().toPath());
            }
            if (dataFile.createNewFile()) {
                System.out.println("File created: " + dataFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    protected static void writeToFile(String textToAdd, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, isAppend);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    protected static void saveTasks(ArrayList<Task> tasks) {
        try {
            DataFile.writeToFile(tasks.get(0).toString(), false);
        } catch (IOException e) {
            System.out.println("⊙﹏⊙ Not able to save your task.");
        }
        int i = 1;
        while (i < tasks.size()) {
            try {
                DataFile.writeToFile(tasks.get(i).toString(), true);
                i += 1;
            } catch (IOException e) {
                System.out.println("⊙﹏⊙ Not able to save your task.");
            }
        }
    }

    protected static void loadFile(ArrayList<Task> tasks) {
        File dataFile = new File(FILE_PATH);
            try {
                Scanner s = new Scanner(dataFile); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    loadTask(s.nextLine(), tasks);
                }
            } catch (FileNotFoundException e) {
                createFile();
            }
        }

    protected static void loadTask(String line, ArrayList<Task> tasks) {
        line = line.replace("[", "");
        line = line.replace("]", "");
        boolean isCompleted;
        isCompleted = (line.charAt(1) == ('X'));
        if (line.startsWith("T")) {
            line = line.substring(2);
            line = line.trim();
            tasks.add(new ToDo(line, isCompleted));
        } else if (line.startsWith("D")) {
            line = line.substring(2);
            line = line.trim();
            String[] taskAndDate = line.split("\\(");
            taskAndDate[0] = taskAndDate[0].trim();
            taskAndDate[1] = taskAndDate[1].replace(")", "");
            taskAndDate[1] = taskAndDate[1].replace(":", "");
            taskAndDate[1] = taskAndDate[1].trim();
            tasks.add(new Deadline(taskAndDate[0], taskAndDate[1], isCompleted));
        } else if (line.startsWith("E")) {
            line = line.substring(2);
            line = line.trim();
            String[] taskAndDate = line.split("\\(from:|to:");
            for (String part : taskAndDate) {
                part = part.trim();
            }
            taskAndDate[2] = taskAndDate[2].substring(0, taskAndDate[2].length() - 1);
            String startDate = "from" + taskAndDate[1];
            String endDate = "to" + taskAndDate[2];
            tasks.add(new Event(taskAndDate[0], startDate, endDate, isCompleted));
        }
    }
}
