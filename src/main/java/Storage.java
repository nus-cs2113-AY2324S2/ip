import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class manages the loading and saving of tasks to a file.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
/**
 * Loads tasks from the file specified by the file path.
 *
 * @return An ArrayList containing the loaded tasks.
 * @throws BrennanException If an error occurs while loading tasks.
 */
public ArrayList<Task> load() throws BrennanException {
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
        // Handle the case where the file doesn't exist
        System.err.println("File not found. Created a new file!");
        createNewFile();
    } catch (IOException e) {
        BrennanException.handleException(e, "null");
    }
    return tasks;
}

/**
 * Saves the provided tasks to the file specified by the file path.
 *
 * @param tasks The ArrayList of tasks to be saved.
 */
public static void save(ArrayList<Task> tasks) {
    try {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            //If the folder does not exist, create it
            file.getParentFile().mkdirs();
        }
        //If the file doesn't exist, create it
        file.createNewFile();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

/**
 * Creates a new file and folder if they don't exist.
 */
private static void createNewFile() {
    try {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            // If the folder does not exist, create it
            file.getParentFile().mkdirs();
        }
        // Create the file if it doesn't exist
        file.createNewFile();
    } catch (IOException ioException) {
        System.err.println("Creation of a new file has failed: " + ioException.getMessage());
    }
}
}
