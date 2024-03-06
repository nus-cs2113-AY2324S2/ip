package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import parsers.Parser;

/**
 * Handles the loading and saving of task list data to a file.
 * This class encapsulates all file operations, ensuring tasks are stored
 * and can be retrieved after restarting sessions.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with a specified file path for data storage.
     *
     * @param filePath The path of the file where tasks are stored and retrieved from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file into an ArrayList of Task objects.
     * If the file does not exist, initialise an empty task list.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws FileNotFoundException If the file specified by filePath does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! No saved tasks found, starting with an empty list ~");
            new File(file.getParent()).mkdirs();
        }

        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the file specified by filePath.
     * If the file or directory does not exist, it will be created.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     * @throws IOException If an error occurs during writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

}
