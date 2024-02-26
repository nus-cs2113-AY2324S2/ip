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

/**
 * Handles the storage of tasks in a file, including reading from and writing to the file.
 * Ensures that tasks persist between application sessions by saving them to a specified file path.
 */
public class Storage {

    /**
     * Saves the current list of tasks to the specified file.
     * Iterates over each task in the list and writes its string representation to the file.
     *
     * @param filePath The path of the file where tasks will be saved.
     */
    public static void saveTasksToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : list) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("     An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Ensures that the directory for the specified file path exists.
     * If the directory does not exist, it is created.
     *
     * @param filePath The file path whose directory should be checked and possibly created.
     */
    public static void ensureDirectoryExists(String filePath) {
        File file = new java.io.File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Creates a new file at the specified file path.
     * Before creating the file, it ensures that the directory path exists.
     *
     * @param filePath The path of the file to be created.
     */
    public static void createFile(String filePath) {
        ensureDirectoryExists(filePath);
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException e) {
            System.out.println("     An error occurred while creating file: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from a file and adds them to the task list.
     * Parses each line of the file to reconstruct tasks and their states (done or not done).
     */
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

    /**
     * Parses a line from the tasks file and reconstructs the corresponding Task object.
     *
     * @param line   The line from the tasks file representing a task.
     * @param isDone The completion status of the task.
     * @return A new instance of {@link Task}, which can be a {@link Todo}, {@link Deadline}, or {@link Event}.
     */
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
