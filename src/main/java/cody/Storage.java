package cody;

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
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws CodyException{
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
            throw new CodyException("An error occurred while writing to the file. Your tasks are not saved");
        }
    }

    public ArrayList<Task> load() throws CodyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            Ui.printMessage(" You have " + tasks.size() + " tasks saved in your list. Enter 'list' to view them");
        } catch (FileNotFoundException e) {
            throw new CodyException(" You have no tasks saved. A new task list will be created for you");
        }
        return tasks;
    }

    private Task parseFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid saved task format: " + line);
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (TaskType.valueOf(type)) {
        case T:
            task = new Todo(description);
            break;
        case D:
            task = parseDeadline(description);
            break;
        case E:
            task = parseEvent(description);
            break;
        default:
            throw new IllegalArgumentException("Unknown saved task type: " + type);
        }

        if (isDone) {
            task.markTask(true);
        }

        return task;
    }

    private Task parseDeadline(String description) {
        String[] parts = description.split(" \\(by: ");
        String taskDescription = parts[0].trim();
        String by = parts.length > 1 ? parts[1].replace(")", "").trim() : "No deadline specified";
        return new Deadline(taskDescription, by);
    }

    private Task parseEvent(String description) {
        String[] parts = description.split(" \\(from: | to: ");
        String taskDescription = parts[0].trim();
        String from = parts.length > 1 ? parts[1].trim() : "No start time specified";
        String to = parts.length > 2 ? parts[2].replace(")", "").trim() : "No end time specified";
        return new Event(taskDescription, from, to);
    }

    private enum TaskType {
        T, D, E
    }
}

