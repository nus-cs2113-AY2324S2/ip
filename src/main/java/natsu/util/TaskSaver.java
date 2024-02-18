package natsu.util;

import natsu.task.Deadline;
import natsu.task.Event;
import natsu.task.Task;
import natsu.task.Todo;

import static natsu.util.TaskManager.list;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;

public class TaskSaver {
    public static void saveTasksToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("     An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    public static void ensureDirectoryExists(String filePath) {
        File file = new java.io.File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public static void createFile(String filePath) {
        ensureDirectoryExists(filePath);
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException e) {
            System.out.println("     An error occurred while creating file: " + e.getMessage());
        }
    }

    public static void readFile() {
        try {
            String filePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt").toString();
            java.io.File file = new java.io.File(filePath);
            if (!file.exists()) {
                createFile(filePath);
            }
            java.util.Scanner input = new java.util.Scanner(file);
            while (input.hasNext()) {
                String line = input.nextLine();
                boolean isDone = line.contains("[X]");
                String content = line.substring(7);
                Task task;
                if (content.startsWith("[D]")) {
                    String by = content.substring(content.indexOf("(by: ") + 5, content.length() - 1);
                    String description = content.substring(0, content.indexOf(" (by:"));
                    task = new Deadline(description, by);
                } else if (content.startsWith("[E]")) {
                    String times = content.substring(content.indexOf("(from: ") + 7);
                    String start = times.substring(0, times.indexOf(" to:"));
                    String end = times.substring(times.indexOf("to: ") + 4, times.length() - 1);
                    String description = content.substring(0, content.indexOf(" (from:"));
                    task = new Event(description, start, end);
                } else {
                    task = new Todo(content);
                }
                if (isDone) {
                    task.markAsDone();
                }
                list.add(task);
            }
            input.close();
        } catch (IOException e) {
            System.out.println("     An error occurred while reading file: " + e.getMessage());
        }
    }
}
