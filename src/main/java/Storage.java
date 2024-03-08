import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object to handle data persistence.
     * @param filePath The file path where tasks are saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     * @return An ArrayList of tasks loaded from the file.
     * @throws DukeException If there is an error parsing the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = createTaskFromData(parts);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, an empty list will be returned
        }

        return loadedTasks;
    }

    /**
     * Creates a Task object from the data stored in the file.
     * @param parts An array of strings containing task data.
     * @return A task object corresponding to the data provided.
     * @throws DukeException If the data is invalid.
     */
    private Task createTaskFromData(String[] parts) throws DukeException {
        Task task = null;
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];

        switch (parts[0]) {
            case "T":
                task = new Task(description);
                break;
            case "D":
                if (parts.length < 4) throw new DukeException("Invalid data for deadline task.");
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                if (parts.length < 5) throw new DukeException("Invalid data for event task.");
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new DukeException("Unknown task type in data file.");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Saves the list of tasks to the file specified by {@code filePath}.
     * @param tasks The list of tasks to be saved.
     * @throws DukeException If there is an error saving tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(filePath);

            for (Task task : tasks) {
                writer.println(taskToFileString(task));
            }

            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error saving tasks to file.");
        }
    }

    /**
     * Converts a Task object into a string format for file storage.
     * @param task The Task object that has to be converted.
     * @return A string representation of the Task for file storage.
     */
    private String taskToFileString(Task task) {
        String type = "T";
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        String additionalInfo = "";

        if (task instanceof Deadline) {
            type = "D";
            additionalInfo = " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            type = "E";
            additionalInfo = " | " + ((Event) task).getStart() + " | " + ((Event) task).getEnd();
        }

        return type + " | " + isDone + " | " + description + additionalInfo;
    }
}
