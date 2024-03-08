package Yoj.storage;
import Yoj.tasks.*;
import Yoj.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private static final String FILE_PATH = "src/data/Yojdata.txt";
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                // Expected format: Type | Completed | Description
                if (parts.length < 3) {
                    break;
                }
                String type = parts[0].trim();
                boolean isCompleted = parts[1].trim().equals("1");
                String description = parts[2].trim();
                Task task = null;
                switch (type) {
                case "[T]":
                    task = new ToDo(description);
                    break;
                case "[D]":
                    String[] deadlineParts = description.split(" by ");
                    if (deadlineParts.length == 2) {
                        task = new Deadline(deadlineParts[0], deadlineParts[1]);
                    }
                    break;
                case "[E]":
                    if (parts.length < 5) return null;
                    String times = parts[3].trim();
                    String[] timeParts = times.split(" to ", 2);
                    if (timeParts.length < 2) return null;
                    task = new Event(description, timeParts[0], timeParts[1]);
                    break;
                }
                if (task != null) {
                    if (isCompleted) {
                        task.markDone();
                    }
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            Ui.printFilenotFound();
        }
        return loadedTasks;
    }

    public static void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH); // create a FileWriter in append mode
        try {
            for (Task task : tasks) { // loop through the ArrayList
                String textToWrite = task.taskType() + " | " + task.isCompleted() + " | " + task.getDescription() + System.lineSeparator();
                fw.write(textToWrite); // write the task to the file
            }
        } finally {
            fw.close();
        }
    }

}

