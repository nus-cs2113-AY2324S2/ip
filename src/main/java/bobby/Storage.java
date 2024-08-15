package bobby;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class handles reading from and writing to a file.
 */
public class Storage {
    private String filePath;
    private Ui ui = new Ui();

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file and data folder if it does not exist.
     */
    public void createFileAndFolder() {
        try {
            File f = new File(filePath);
            File parentDir = f.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            f.createNewFile();
        } catch (IOException e) {
            ui.showTextFileError();
        }
    }

    private static void readFile(String line, ArrayList<Task> tasks) {
        String[] parts = line.split("\\|");
        String label = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        switch (label) {
        case "T":
            tasks.add(new Todo(description, isDone));
            break;
        case "D":
            String by = parts[3].trim();
            tasks.add(new Deadline(description, isDone, by));
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            tasks.add(new Event(description, isDone, from, to));
            break;
        default:
        }
    }

    /**
     * Loads tasks from the file into the provided ArrayList.
     *
     * @param tasks The ArrayList to store the loaded tasks.
     * @throws FileNotFoundException If the file is not found.
     */
    public void loadFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            readFile(line, tasks);
        }
    }

    /**
     * Converts a Task object into a string format suitable for writing to the file.
     *
     * @param task The Task object to convert.
     * @return The string representation of the Task object.
     */
    public static String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + "\n";
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getBy() + "\n";
        } else if (task instanceof Event) {
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getFrom() + " | "
                    + task.getBy() + "\n";
        }
        return "";
    }

    /**
     * Writes tasks from the provided ArrayList to the file.
     *
     * @param tasks The ArrayList containing the tasks to write.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(taskToFileFormat(task));
        }
        fw.close();
    }
}
