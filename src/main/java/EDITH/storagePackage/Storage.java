package EDITH.storagePackage;

import EDITH.taskListPackage.TaskList;
import EDITH.taskPackage.Deadlines;
import EDITH.taskPackage.Events;
import EDITH.taskPackage.Task;
import EDITH.taskPackage.ToDos;
import EDITH.ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks from/to a file.
 */
public class Storage {
    private static final String FILENAME = "text.txt";
    private static final String DIRECTORY = "data";

    /**
     * Loads tasks from the file.
     *
     * @return The list of tasks loaded from the text file.
     */
    public List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        Path filePath = getFilePath();

        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    Task task = parseTaskFromLine(line);
                    tasks.add(task);
                }
                System.out.println("Tasks have been loaded from " + filePath);
            } else {
                System.out.println("No tasks file found at " + filePath);
                // Create the file if it doesn't exist
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                System.out.println("New tasks file created at " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from " + filePath);
            e.printStackTrace();
        }
        return tasks;
    }

    private Path getFilePath() {
        String currentDirectory = System.getProperty("user.dir");
        String relativeFilePath = Paths.get(currentDirectory, DIRECTORY, FILENAME).toString();
        return Paths.get(relativeFilePath);
    }

    private Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String additionalInfo = (parts.length > 3) ? parts[3] : "";

        switch (type) {
        case "T":
            return new ToDos(description, isDone);
        case "D":
            return new Deadlines(description, additionalInfo, isDone);
        case "E":
            return parseEvent(description, additionalInfo, isDone);
        default:
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

    private Task parseEvent(String description, String additionalInfo, boolean isDone) {
        String fromDate = "";
        String toDate = "";

        if (!additionalInfo.isEmpty()) {
            String[] eventInfo = additionalInfo.split(" to ");
            if (eventInfo.length == 2) {
                fromDate = eventInfo[0].replace("from ", "").trim();
                toDate = eventInfo[1].trim();
            } else if (eventInfo.length == 1) {
                String[] fromDateParts = eventInfo[0].split("from ");
                if (fromDateParts.length == 2) {
                    fromDate = fromDateParts[1].trim();
                }
                toDate = "No specific end time";
            }
        }
        return new Events(description, fromDate, toDate, isDone);
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @param ui    The Ui object for displaying messages.
     */
    public void saveTasksToFile(TaskList tasks, Ui ui) {
        String currentDirectory = System.getProperty("user.dir");
        String relativeFilePath = Paths.get(currentDirectory, DIRECTORY, FILENAME).toString();
        Path filePath = Paths.get(relativeFilePath);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            for (Task task : tasks.getList()) {
                Files.write(filePath, (task.toFileString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            }
            ui.printFormattedMessage("Tasks have been saved");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to " + filePath);
            e.printStackTrace();
        }
    }
}
