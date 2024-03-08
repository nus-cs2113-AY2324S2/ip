import java.io.*;
import java.util.ArrayList;

/**
 * Storage class handles loading and saving tasks from/to a file for the Duke application.
 */
public class Storage {

    /** File path for storing and loading tasks. */
    private String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath File path for storing and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file path. If the file or its parent directory does not exist,
     * attempts to create them before reading tasks. Converts each line in the file to a Task object
     * and adds it to the ArrayList of tasks.
     *
     * @return ArrayList of tasks loaded from the file.
     * @throws DukeException If an error occurs during file creation, access, reading, or if the file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Error creating or accessing the file: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to the specified file path. If the file or its parent directory does not exist,
     * attempts to create them before saving the tasks.
     *
     * @param tasks ArrayList of tasks to be saved to the file.
     * @throws DukeException If an error occurs during file creation, access, or writing.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Error creating or accessing the file: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }

}
