import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class responsible for handling file storage operations.
 */
public class Storage {
    private static String FILE_PATH;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    /**
     * Saves tasks to the file.
     */
    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : TaskList.getList()) {
                fw.write(taskToLine(task) + "\n");
            }
            fw.close();
            System.out.println("Saved to file");
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @throws FileNotFoundException    If the file is not found.
     * @throws InvalidInputException    If there is an invalid input in the file.
     */
    public void loadTasks() throws FileNotFoundException, InvalidInputException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Parser.parseFileLine(line);
        }
        scanner.close();
    }

    /**
     * Converts a task object to a string representation for writing to the file.
     *
     * @param task The task object to convert.
     * @return A string representing the task for writing to the file.
     */
    private static String taskToLine(Task task) {
        if (task instanceof ToDo todo) {
            return "T | " + (todo.isDone() ? "1" : "0") + " | " + todo.getDescription();
        } else if (task instanceof Deadline deadline) {
            return "D | " + (deadline.isDone() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Events event) {
            return "E | " + (event.isDone() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
}
