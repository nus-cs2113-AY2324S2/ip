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
     * Loads tasks from the file specified by the file path.
     *
     * @return ArrayList of tasks loaded from the file.
     * @throws DukeException If an error occurs during file reading or if the file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File cannot be found.");
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file.");
        }

        return tasks;
    }

    /**
     * Saves tasks to the file specified by the file path.
     *
     * @param tasks ArrayList of tasks to be saved to the file.
     * @throws DukeException If an error occurs during file writing.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file.");
        }
    }
}
