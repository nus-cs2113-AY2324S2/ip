package cody.storage;

import cody.CodyException;
import cody.tasks.Deadline;
import cody.tasks.Event;
import cody.tasks.Task;
import cody.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final int MINIMUM_PARTS = 3;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws CodyException {
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                String taskType = task.getTaskType();
                String isDone = task.getStatusIcon().equals("X") ? "1" : "0";
                String description = task.getDescription();

                writer.write(taskType + " | " + isDone + " | " + description + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new CodyException("Error saving tasks to file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws CodyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = readFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new CodyException("You have no tasks saved");
        }
        return tasks;
    }

    private Task readFile(String line) throws CodyException {
        String[] parts = line.split(" \\| ");
        if (parts.length < MINIMUM_PARTS) {
            throw new CodyException("Invalid saved task format: " + line);
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = readDeadline(description);
            break;
        case "E":
            task = readEvent(description);
            break;
        default:
            throw new CodyException("Unknown saved task type: " + type);
        }

        if (isDone) {
            task.markTask(true);
        }

        return task;
    }

    private Task readDeadline(String description) throws CodyException {
        String[] parts = description.split(" \\(by: ");
        if (parts.length < 2) {
            throw new CodyException("Invalid deadline format: " + description);
        }
        String taskDescription = parts[0].trim();
        String by = parts[1].replace(")", "").trim();
        return new Deadline(taskDescription, by);
    }

    private Task readEvent(String description) throws CodyException {
        String[] parts = description.split(" \\(from: | to: ");
        if (parts.length < 2) {
            throw new CodyException("Invalid event format: " + description);
        }
        String taskDescription = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].replace(")", "").trim();
        return new Event(taskDescription, from, to);
    }
}
