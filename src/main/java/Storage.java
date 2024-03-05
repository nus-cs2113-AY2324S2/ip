import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles loading tasks from file and saving tasks to file.
 */
public class Storage {
    // File path for saving tasks
    private static final String FILE_PATH = "tasks.txt";

    /**
     * Loads tasks from file into the provided task list.
     *
     * @param tasks The task list to populate with loaded tasks.
     */
    public static void loadTasksFromFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] taskInfo = line.split("\\|");
                    String taskType = taskInfo[0].trim();
                    boolean isDone = taskInfo[1].trim().equals("1");
                    String taskDescription = taskInfo[2].trim();
                    // Add task to list based on type
                    switch (taskType) {
                        case "T":
                            tasks.add(new Todo(taskDescription, isDone));
                            break;
                        case "D":
                            tasks.add(new Deadline(taskDescription, taskInfo[3].trim(), isDone));
                            break;
                        case "E":
                            tasks.add(new Event(taskDescription, taskInfo[3].trim(), taskInfo[4].trim(), isDone));
                            break;
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Saves tasks from the provided task list to file.
     *
     * @param tasks The task list to save to file.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            // Create file if it does not exist
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
