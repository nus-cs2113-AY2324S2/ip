import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 * This class is responsible for reading from and writing to the task storage file,
 * converting between the file format and Task objects.
 */
public class Storage {
    private final String filePath; // The file path where tasks are stored.

    /**
     * Constructs a Storage object associated with a specific file path.
     *
     * @param filePath the path to the file used for task storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * Reads the task file line by line, parsing each line into a Task object,
     * and returns a list of all tasks loaded from the file.
     *
     * @return an ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = parseLineToTask(line);
                    if (task != null) {
                        loadedTasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.println("Unable to read tasks from file: " + e.getMessage());
            }
        }
        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     * Writes each task to the file in a format suitable for later loading.
     *
     * @param tasks the list of Task objects to be saved to the file.
     * @throws IOException if an I/O error occurs writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }

    /**
     * Parses a single line from the task file into a Task object.
     * This method is used internally when loading tasks from the file.
     *
     * @param line the line from the file to parse into a Task object.
     * @return the Task object parsed from the line, or null if the line could not be parsed.
     */
    private Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        Task task = null;

        try {
            switch (type) {
            case "T":
                task = new Todo(parts[2].trim());
                break;
            case "D":
                task = new DeadLine(parts[2].trim(), parts[3].trim());
                break;
            case "E":
                task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                break;
            }

            if (task != null) {
                boolean isDone = parts[1].trim().equals("1");
                task.setDone(isDone);
            }
        } catch (Exception e) {
            System.err.println("Failed to parse line into task: " + line);
        }

        return task;
    }
}
