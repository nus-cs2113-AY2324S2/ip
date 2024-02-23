import Quokka.exceptions.QuokkaException;
import Quokka.tasks.Task;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private static final String DATA_FILE_PATH = "tasks.txt";

    public static void saveTasksToFile(TaskList taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE_PATH))) {
            for (int i = 0; i < taskList.size(); i++) {
                writer.println(taskList.get(i).toString());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file: " + e.getMessage());
        }
    }

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
