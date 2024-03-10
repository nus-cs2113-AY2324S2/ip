import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks to the file.
 */
public class Storage {

    private String filePath;
    private static Ui ui = new Ui();

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file into the provided TaskList.
     *
     * @param tasks The TaskList to load tasks into.
     */
    public void loadTasksFromFile(TaskList tasks) {
        try {
            Path path = Paths.get(filePath);

            if (!Files.exists(path)) {
                // Handle the case where the file doesn't exist yet
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                ui.showFileCreated();
            } else {
                File savedFile = new File(filePath);
                Scanner scanner = new Scanner(savedFile);

                System.out.println("Tasks loaded from file:");
                while (scanner.hasNext()) {
                    String fileLine = scanner.nextLine();
                    System.out.println("Read from file: " + fileLine);

                    char taskType = fileLine.charAt(1);
                    tasks.addTaskFromSaved(fileLine, taskType);
                }
                ui.showTasksLoaded();
                tasks.printTaskList();
                scanner.close();
            }
        } catch (IOException e) {
            ui.handleErrors(e);
        }
    }

    /**
     * Saves tasks from the provided TaskList to the specified file.
     *
     * @param tasks The TaskList to save tasks from.
     */
    public void saveTasksToFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            System.out.println("Saving tasks to file...");
            for (int i = 0; i < tasks.size(); i++) {
                String taskString = tasks.get(i).toString();
                writer.write(taskString + System.lineSeparator());
            }
            writer.close();
            ui.showTasksSaved();
        } catch (IOException e) {
            ui.handleErrors(e);
        }
    }
}
