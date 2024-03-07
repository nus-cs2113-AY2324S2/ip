import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class handles file operations for saving and loading tasks.
 */
public class Storage {
    private static String FILE_PATH;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
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
     * @throws FileNotFoundException    If the tasks file is not found.
     * @throws InvalidInputException    If there is an invalid input in the file.
     */
    public void loadTasks() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            createFileAndFolder();
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Parser.parseFileLine(line);
            }
            scanner.close();
        } catch (InvalidInputException e) {
            // If invalid input is encountered, delete the file and create a new one
            file.delete();
            createFileAndFolder();
            System.out.println("Invalid input format in file. Deleting file and creating a new one.");
        }
    }

    /**
     * Creates the file and its parent folder if they don't exist.
     */
    private void createFileAndFolder() {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.createNewFile();
            System.out.println("Folder and file created at: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error creating folder and file: " + e.getMessage());
        }
    }

    /**
     * Converts a task object to a string representation for writing to the file.
     *
     * @param task The task object to convert.
     * @return A string representing the task for writing to the file.
     */
    private static String taskToLine(Task task) {
        if (task instanceof ToDo) {
            ToDo todo = (ToDo) task;
            return "T | " + (todo.isDone() ? "1" : "0") + " | " + todo.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (deadline.isDone() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Events) {
            Events event = (Events) task;
            return "E | " + (event.isDone() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
}
