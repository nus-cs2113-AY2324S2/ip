package Helper;

import Exceptions.LoadFileException;
import Exceptions.SaveFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles loading and saving tasks to a file.
 */

public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object with the given file path.
     *
     * @param filePath The path to the file storing tasks.
     */

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws LoadFileException If an error occurs while loading tasks from the file.
     */

    public ArrayList<Task> load() throws LoadFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new LoadFileException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file specified by the file path.
     *
     * @param taskList The list of tasks to be saved to the file.
     * @throws SaveFileException If an error occurs while saving tasks to the file.
     */

    public void saveTasksToFile(ArrayList<Task> taskList) throws SaveFileException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new SaveFileException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
