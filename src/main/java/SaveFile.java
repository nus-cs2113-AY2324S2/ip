import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFile {
    private static final String FILE_PATH = "./data/duke.txt";
    static ArrayList<Task> tasks = Duke.tasks;

    public static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("No existing data file found. Starting with an empty task list.");
        }
    }

    public static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }
}