import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Storage class handles saving and loading tasks from a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Saves tasks to a file.
     *
     * @param tasksList The list of tasks to save.
     */
    public void saveTasks(ArrayList<Task> tasksList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create directories if they don't exist
            }
            file.createNewFile(); // Create the file if it doesn't exist
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < tasksList.size(); i++) {
                    writer.write(tasksList.get(i).toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            KratosException.handleException(e, "Tasks seek refuge. Stitch the tapestry of storage.\n" + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file.
     *
     * @return The list of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasksList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasksList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist
            KratosException.handleException(e, Ui.fileNotFound);
            File file = new File(FILE_PATH);
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs(); // Create directories if they don't exist
                }
                file.createNewFile();
            } catch (IOException ioException) {
                KratosException.handleException(ioException, Ui.folderNotFound +
                        ioException.getMessage());
            }
        } catch (IOException e) {
            KratosException.handleException(e,Ui.fileReadingError +
                    e.getMessage());
        }
        return tasksList;
    }
}
