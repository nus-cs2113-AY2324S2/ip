import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * The Storage class handles loading and saving tasks to a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * This method loads the tasks saved in the storage file
     * @return tasks which are loaded from the saved file
     */
    public static List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                Task task;
                switch (taskType) {
                    case "T":
                        task = new TodoTask(description);
                        if (isDone){
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    case "D":
                        task = new DeadlineTask(description, parts[3].trim());
                        if (isDone){
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    case "E":
                        task = new EventTask(description, parts[3].trim(), parts[4].trim());
                        if (isDone){
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    default:
                        System.out.println("Invalid task type found in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * This method saves tasks into the storage files
     * @param tasks the current tasks needed to be saved
     */
    public static void saveTasksToFile(List<Task> tasks) {
        try {
            // Create directory if it doesn't exist
            Path directoryPath = Paths.get("./data");
            Files.createDirectories(directoryPath);

            // Write tasks to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Task task : tasks) {
                    String line;
                    if (task instanceof TodoTask) {
                        line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                    } else if (task instanceof DeadlineTask) {
                        DeadlineTask deadlineTask = (DeadlineTask) task;
                        line = "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getBy();
                    } else if (task instanceof EventTask) {
                        EventTask eventTask = (EventTask) task;
                        line = "E | " + (task.isDone() ? "1" : "0") + " | " + eventTask.getDescription() + " | " + eventTask.getFrom() + " | " + eventTask.getTo();
                    } else {
                        continue; // Skip unknown task types
                    }
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
