package cody;

import cody.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save {

    private static final String FILE_PATH = "./data/tasks.txt"; // Define the file path here

    public static void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                String taskType = task.getTaskType();
                String isDone = task.getStatusIcon().equals("X") ? "1" : "0";
                String description = task.getDescription();

                writer.write(taskType + " | " + isDone + " | " + description + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. Your tasks are not saved.");
        }
    }
}
