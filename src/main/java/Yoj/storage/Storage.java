package Yoj.storage;

import Yoj.tasks.*;
import Yoj.ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = makeFilePath();

    private static String makeFilePath() {
        String userHome = System.getProperty("user.home");
        String separator = FileSystems.getDefault().getSeparator();    // Construct the directory path separately
        String directoryPath = userHome + separator + "Desktop" + separator + "Yoj";
        File YojDirectory = new File(directoryPath);
        // Ensure the directory exists
        if (!YojDirectory.exists()) {
            boolean wasSuccessful = YojDirectory.mkdirs(); // Create the directory if it doesn't exist
            if (wasSuccessful) {
                Ui.printCreateFile();
            } else {
                Ui.printFileCreationFailed();
            }
        }
        String filePath = directoryPath + separator + "Yojdata.txt";
        return filePath;
    }
    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
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
                    String[] eventDetails = parts[2].trim().split(" from ");
                    if (eventDetails.length < 2) {
                        return null;
                    }
                    String eventDescription = eventDetails[0].trim();
                    String[] timeDetails = eventDetails[1].trim().split(" to ");
                    if (timeDetails.length < 2) {
                        return null;
                    }
                    String startTime = timeDetails[0].trim();
                    String endTime = timeDetails[1].trim();
                    task = new Event(eventDescription, startTime, endTime);
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
    /**
     * Saves the current tasks to the storage file.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public static void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH); // create a FileWriter in append mode
        try {
            for (Task task : tasks) { // loop through the ArrayList
                String textToWrite = task.taskType() + " | " + task.isCompleted();
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    textToWrite += " | " + deadlineTask.getDescription() + " by " + deadlineTask.getBy();
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    textToWrite += " | " + eventTask.getDescription() + " from " + eventTask.getStart() + " to " + eventTask.getEnd();
                } else {
                    textToWrite += " | " + task.getDescription();
                }
                textToWrite += System.lineSeparator();
                fw.write(textToWrite); // write the task to the file
            }
        } finally {
            fw.close();
        }
    }

}

