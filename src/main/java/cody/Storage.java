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

    private static final String FILE_PATH = "./data/tasks.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);

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
            System.err.println("An error occurred while writing to the file. Your tasks are not saved");
        }
    }

    public static void loadTasksFromFile(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String[] deadlineParts = description.split(" \\(by: ");
                    String deadlineDescription = deadlineParts[0].trim();
                    String by = deadlineParts.length > 1 ? deadlineParts[1].replace(")", "").trim() : "No deadline specified";
                    task = new Deadline(deadlineDescription, by);
                    break;
                case "E":
                    String[] eventParts = description.split(" \\(from: | to: ");
                    String eventDescription = eventParts[0].trim();
                    String from = eventParts.length > 1 ? eventParts[1].trim() : "No start time specified";
                    String to = eventParts.length > 2 ? eventParts[2].replace(")", "").trim() : "No end time specified";
                    task = new Event(eventDescription, from, to);
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markTask(true);
                    }
                    tasks.add(task);
                }
            }
            System.out.println(" You have " + tasks.size() + " tasks saved in your list. Enter 'list' to view them");
        } catch (FileNotFoundException e) {
            System.out.println(" You have no saved tasks. A new task list will be created for you");
        }
    }
}
