import Quokka.exceptions.QuokkaException;
import Quokka.tasks.Task;

import java.io.*;
import java.util.Scanner;

/**
 * The Storage class handles the saving and loading of tasks to/from a data file.
 */
public class Storage {
    private static final String DATA_FILE_PATH = "tasks.txt";

    /**
     * Saves the tasks in the task list to a data file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     */
    public static void saveTasksToFile(TaskList taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE_PATH))) {
            for (int i = 0; i < taskList.size(); i++) {
                writer.println(taskList.get(i).toString());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the data file into the task list.
     *
     * @param taskList The TaskList where loaded tasks will be stored.
     * @param maxTasks The maximum number of tasks to load from the data file.
     */
    public static void loadTasksFromFile(TaskList taskList, int maxTasks) {
        File file = new File(DATA_FILE_PATH);
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with empty task list.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine() && taskList.size() < maxTasks) {
                String taskData = scanner.nextLine();
                Task task = Task.parseFromFileString(taskData);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found: " + e.getMessage());
        } catch (QuokkaException e) {
            System.out.println("Data file contains corrupted data: " + e.getMessage());
        }
    }
}
