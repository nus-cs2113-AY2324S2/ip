package storagePackage;

import taskListPackage.TaskList;
import taskPackage.Deadlines;
import taskPackage.Events;
import taskPackage.Task;
import taskPackage.ToDos;

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
    private static final String DIRECTORY = "src/data";

    /**
     * Loads tasks from the file.
     *
     * @return The list of tasks loaded from the text file.
     */
    public List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();

        String currentDirectory = System.getProperty("user.dir");
        String relativeFilePath = Paths.get(currentDirectory, DIRECTORY, FILENAME).toString();
        Path filePath = Paths.get(relativeFilePath);

        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {

                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    String additionalInfo = "";

                    if (parts.length > 3) {
                        additionalInfo = parts[3];
                    }

                    Task task;
                    switch (type) {
                    case "T":
                        task = new ToDos(description, isDone);
                        break;
                    case "D":
                        task = new Deadlines(description, additionalInfo, isDone);
                        break;
                    case "E":
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
                        task = new Events(description, fromDate, toDate, isDone);
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid task type: " + type);
                    }
                    tasks.add(task);
                }
                System.out.println("Tasks have been loaded from " + filePath);
            } else {
                System.out.println("No tasks file found at " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from " + filePath);
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public void saveTasksToFile(TaskList tasks) {
        String currentDirectory = System.getProperty("user.dir");
        String relativeFilePath = Paths.get(currentDirectory, DIRECTORY, FILENAME).toString();
        Path filePath = Paths.get(relativeFilePath);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            for (Task task : tasks.getList()) {
                Files.write(filePath, (task.toFileString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            }
            System.out.println("Tasks have been saved to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to " + filePath);
            e.printStackTrace();
        }
    }

}
