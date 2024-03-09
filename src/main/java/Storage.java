import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks to the file.
 */
public class Storage {

    private String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    private static void loadTasksFromFile() {
        try {
            Path path = Paths.get(Sunny.FILE_PATH);

            if (!Files.exists(path)) {
                // Handle the case where the file doesn't exist yet
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                System.out.println("File created: " + FILE_PATH);
            }


            Scanner scanner = new Scanner(new File(FILE_PATH));
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                System.out.println("Read from file: " + fileLine);

                // Parse and add the task directly
                parseAndAddTask(fileLine);
            }
            // Print the loaded tasks
            printTaskList();

            System.out.println("Tasks loaded successfully!");
        } catch (IOException e) {
            handleErrors(e);
        }
    }

    private static void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (Task task : tasksList) {
                writer.println(task.toFileString());
            }
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            handleErrors(e);
        }
    }

    private static void parseAndAddTask(String data) {
        String[] parts = data.split(" \\| ");

        if (parts.length < 3) {
            // Not enough data to create a valid task
            return;
        }

        String type = parts[0].toLowerCase(); // Convert to lowercase for case-insensitivity
        String status = parts[1];
        String description = parts[2];

        switch (type) {
        case "t":
            tasksList.add(Todo.fromFileFormat(description));
            break;
        case "d":
            tasksList.add(Deadline.fromFileFormat(description));
            break;
        case "e":
            tasksList.add(Event.fromFileFormat(description));
            break;
        default:
            // Unknown task type
            break;
        }

    }
    /**
     * Loads tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     */
    public List<Task> loadTasks() {
        // Your implementation to load tasks from the file
        List<Task> loadedTasks;
        return loadedTasks;
    }

    public void saveTasks(List<Task> tasks) {
        // Implement your saving logic here
    }
}
