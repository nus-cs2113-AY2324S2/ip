import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the loading and saving of tasks to a data file for the Jane task management application.
 */
public class Storage {
    /** The file path for storing and retrieving task data. */
    public final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file for storing task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks from the provided TaskList to the data file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If an error occurs during file writing.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        Path filePath = Paths.get(this.filePath);

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        List<String> encodedTasks = encodeTasks(tasks.getTasks());
        Files.write(filePath, encodedTasks);
    }

    /**
     * Loads tasks from the data file into a TaskList.
     *
     * @return A TaskList containing tasks loaded from the data file.
     * @throws IOException If an error occurs during file reading.
     * @throws JaneDataCorruptedException If the data file is corrupted.
     */
    public TaskList loadTasksToFile() throws IOException, JaneDataCorruptedException {
        Path filePath = Paths.get(this.filePath);
        TaskList tasks;

        if (Files.exists(filePath)) {
            try {
                List<String> lines = Files.readAllLines(filePath);
                tasks = new TaskList(decodeTasks(lines));
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("Data file not found");
            } catch (IOException e) {
                throw new IOException("Error reading tasks from the file", e);
            } catch (JaneDataCorruptedException e) {
                throw e;
            }
        } else {
            tasks = new TaskList();
        }

        return tasks;
    }

    /**
     * Encodes a list of tasks into a format suitable for writing to the data file.
     *
     * @param tasks The list of tasks to be encoded.
     * @return A list of encoded task strings.
     */
    public static List<String> encodeTasks(List<Task> tasks) {
        List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(encodeTask(task));
        }
        return encodedTasks;
    }

    /**
     * Encodes a single task into a string format suitable for writing to the data file.
     *
     * @param task The task to be encoded.
     * @return The encoded string representation of the task.
     */
    public static String encodeTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getBy();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + eventTask.getDescription() + " | " + eventTask.getStart() + "-" + eventTask.getEnd();
        } else {
            return "";
        }
    }

    /**
     * Decodes a list of task strings from the data file into a list of Task objects.
     *
     * @param lines The list of task strings read from the data file.
     * @return A list of Task objects decoded from the task strings.
     * @throws JaneDataCorruptedException If the data file is corrupted.
     */
    public static ArrayList<Task> decodeTasks(List<String> lines) throws JaneDataCorruptedException {
        ArrayList<Task> decodedTasks = new ArrayList<>();
        try {
            for (String line : lines) {
                decodedTasks.add(decodeTask(line));
            }
        } catch (IllegalArgumentException e) {
            throw new JaneDataCorruptedException("Data file is corrupted: " + e.getMessage());
        }

        return decodedTasks;
    }

    /**
     * Decodes a single task string from the data file into a Task object.
     *
     * @param line The task string read from the data file.
     * @return The Task object decoded from the task string.
     */
    public static Task decodeTask(String line) {
        String[] parts = line.split(" \\| ", 3);
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description;

        switch (taskType) {
        case "T":
            description = parts[2];
            Task newTodo = new Todo(description);
            newTodo.setDone(isDone);
            return newTodo;
        case "D":
            String[] descriptionByString = parts[2].split(" \\| ");
            description = descriptionByString[0];
            String by = descriptionByString[1];
            Deadline newDeadline = new Deadline(description, by);
            newDeadline.setDone(isDone);
            return newDeadline;
        case "E":
            String[] descriptionStartEndString = parts[2].split(" \\| ");
            description = descriptionStartEndString[0];
            String[] dateTimeParts = descriptionStartEndString[1].split("-");
            String start = dateTimeParts[0];
            String end = dateTimeParts[1];

            Event newEvent = new Event(description, start, end);
            newEvent.setDone(isDone);
            return newEvent;
        default:
            return null;
        }
    }

    /**
     * Ensures that the data file exists, creating it if necessary.
     *
     * @throws IOException If an error occurs during file creation.
     */
    public void ensureDataFileExists() throws IOException {
        Path filePath = Paths.get(this.filePath);
        // Check if the file exists or create it
        if (!Files.exists(filePath)) {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.createFile(filePath);
        }
    }
}