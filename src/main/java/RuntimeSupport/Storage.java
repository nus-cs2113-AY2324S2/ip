package RuntimeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Event.Task;
import Event.Deadline;
import Event.ToDo;
import Event.Event;

/**
 * The <code>Storage</code> class manages the storage of task data in a file,
 * including loading tasks from the file and saving tasks to the file.
 * This class also deals with the file handling mechanism required to recall
 * and keep tasks between different sessions.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes a new Storage instance with a specific file path for task data.
     *
     * @param filePath the file path where tasks are saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file. This method reads the file line by line,
     * converting each line into a Task object which is then added to a list of
     * tasks.
     * If the file does not exist, it attempts to create a new file.
     *
     * @return an ArrayList of Task objects representing the tasks loaded from the
     * file.
     * @throws DukeException if an IOException occurs during file operation,
     * encapsulating the error as a DukeException.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        try {
            createFileIfNotExists(file);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processLine(loadedTasks, line);
                }
            }
        } catch (IOException e) {
            throw new DukeException();
        }
        return loadedTasks;
    }

    /**
     * Ensures the storage file exists. If the file does not exist, it creates a
     * new file and notifies the user.
     *
     * @param file the File object representing the storage file.
     * @throws IOException if an error occurs while creating the new file.
     */
    private void createFileIfNotExists(File file) throws IOException {
        if (!file.exists() && file.createNewFile()) {
            System.out.println("No existing storage file detected.");
            System.out.println("New storage file has been created at " + filePath);
        }
    }

    /**
     * Processes a line from the storage file, converting it into a Task object
     * and adding it to the list of tasks.
     *
     * @param loadedTasks the list of tasks loaded so far.
     * @param line the current line being processed from the storage file.
     */
    private void processLine(ArrayList<Task> loadedTasks, String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;
        switch (parts[0]) {
        case "T":
            task = new ToDo("todo " + parts[2]);
            break;
        case "D":
            task = new Deadline("deadline " + parts[2] + " /by " + parts[3]);
            break;
        case "E":
            task = new Event("event " + parts[2] + " /from " + parts[3].split(" to ")[0]
                    + " /to " + parts[3].split(" to ")[1]);
            break;
        }
        if (task != null) {
            if ("1".equals(parts[1])) {
                task.markAsDone();
            }
            loadedTasks.add(task);
        }
    }

    /**
     * Saves the current list of tasks to the storage file. This method overwrites
     * the existing file content with the current state of tasks, each task being
     * written as one line in the file.
     *
     * @param tasks the ArrayList of Task objects to be saved to the file.
     * @throws DukeException if an IOException occurs during the file writing process.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : tasks) {
                writer.write(task.toStorageString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException();
        }
    }
}
