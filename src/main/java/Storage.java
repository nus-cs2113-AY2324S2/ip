import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static List<Task> loadTasks() throws PhoebeException {
        List<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = Parser.parseTaskFromString(data);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // It's not necessarily an error if the file doesn't exist; it could just mean this is the first run.
            Ui.printFileNotFound();
        } catch (Exception e) {
            // For other exceptions, inform the user and rethrow as PhoebeException to be handled or logged by the caller.
            Ui.printCorrupted();
            throw new PhoebeException("Error loading tasks. Data file might be corrupted.");
        }

        return loadedTasks;
    }

    public static void saveTasks(List<Task> tasks) throws PhoebeException {
        try {
            File directory = new File(filePath).getParentFile();
            if (!directory.exists() && !directory.mkdirs()) {
                // If directory creation failed, inform the user and throw an exception
                Ui.printErrorSaving();
                throw new PhoebeException("Failed to create directory for saving tasks.");
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            // For IO exceptions, inform the user and rethrow as PhoebeException to be handled or logged by the caller.
            Ui.printErrorSaving();
            throw new PhoebeException("An error occurred while saving tasks.");
        }
    }
}
