import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for managing the storage of tasks in a file.
 * It provides methods to load tasks from a file and save tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, it creates the file before loading.
     * @return an ArrayList of Task objects loaded from the file
     * @throws DukeException if there is an error loading tasks from the file
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                // If the folder does not exist, create it
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile(); // Create the file if it doesn't exist
            } catch (IOException e) {
                throw new DukeException("Error creating new file: " + e.getMessage());
            }
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
            throw new DukeException("File not found. Creating a new file...");
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the file specified by the file path.
     * If the file does not exist, it creates the file before saving.
     * @param tasks the list of Task objects to be saved to the file
     * @throws DukeException if there is an error saving tasks to the file
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                // If the folder does not exist, create it
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile(); // Create the file if it doesn't exist
            } catch (IOException e) {
                throw new DukeException("Error creating new file: " + e.getMessage());
            }
        }
        try (FileWriter writer = new FileWriter(file)) { // true for append mode
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}


