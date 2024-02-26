package natsu.util;

import natsu.task.Deadline;
import natsu.task.Event;
import natsu.task.Task;
import natsu.task.Todo;

import static natsu.util.TaskList.list;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;

public class Storage {
    public static void saveTasksToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : list) {
                writer.write(task.toString() + "\n");
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
                Task task = getTask(line, isDone);
                list.add(task);
            }
            input.close();
        } catch (IOException e) {
            System.out.println("     An error occurred while reading file: " + e.getMessage());
        }
    }

    private static Task getTask(String line, boolean isDone) {
        Task task;
        if (line.startsWith("[D]")) {
            String by = line.substring(line.indexOf("(by: ") + 5, line.length() - 1);
            String description = line.substring(7, line.indexOf(" (by:"));
            task = new Deadline(description, by);
        } else if (line.startsWith("[E]")) {
            String times = line.substring(line.indexOf("(from: ") + 7);
            String start = times.substring(0, times.indexOf(" to:"));
            String end = times.substring(times.indexOf("to: ") + 4, times.length() - 1);
            String description = line.substring(7, line.indexOf(" (from:"));
            task = new Event(description, start, end);
        } else {
            task = new Todo(line.substring(7));
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
