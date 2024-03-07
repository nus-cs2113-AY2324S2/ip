import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.io.IOException;

/**
 * Storage class deals with loading tasks from file and saving tasks to file.
 */
public class Storage {
    /**
     * The directory path where data files are stored.
     */
    public static final String DIRECTORYPATH = "./data/";

    /**
     * The file path for storing data inputted by user with the file name being "MsChatty:).txt".
     */
    public static final String FILEPATH = DIRECTORYPATH + "MsChatty:).txt";

    /**
     * Adds task to file.
     *
     * @param filePath The path to the file where tasks are stored.
     * @param textToAppend A string containing the task to be added.
     * @throws IOException If an error occurs during appending.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(textToAppend);
        }
    }

    /**
     * Saves all tasks to file.
     *
     * @param tasks The array list containing tasks inputted.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File(DIRECTORYPATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter fileWriter = new FileWriter(FILEPATH, false);
            fileWriter.write("");

            for (Task task : tasks) {
                appendToFile(FILEPATH, task.taskInFileFormat());
            }
        } catch (IOException e) {
            System.out.println(Messages.ERROR_SAVING_DATA_MESSAGE + e.getMessage());
        }
    }

    /**
     * Converts all tasks from file format to list format.
     *
     * @param tasks The array list containing tasks inputted.
     * @return The array list containing tasks in list format.
     */
    public static ArrayList<Task> revertTasksToListFormat(List<String> tasks) {
        ArrayList<Task> revertedTasks = new ArrayList<>();
        try {
            for (String task : tasks) {
                revertedTasks.add(revertTaskToListFormat(task));

            }
        } catch (IllegalArgumentException e) {
            System.out.println(Messages.FILE_CORRUPTED_MESSAGE + e.getMessage());
        }
        return revertedTasks;
    }

    /**
     * Converts individual task from file format to list format.
     *
     * @param line A string representing a task read from file.
     * @return task in list format.
     */
    public static Task revertTaskToListFormat(String line) {
        String itemIsDone = "1";
        String[] arrayOfTask = line.split(" \\| ", 3);
        boolean isDone = arrayOfTask[1].equals(itemIsDone);
        String description;

        switch (arrayOfTask[0]) {
        case "T":
            description = arrayOfTask[2];
            Task newTodo = new Todo(description);
            newTodo.setDone(isDone);
            return newTodo;
        case "D":
            String[] arrayOfDeadlineSubsequentParts = arrayOfTask[2].split(" \\| ");
            Deadline newDeadline = new Deadline(arrayOfDeadlineSubsequentParts[0], arrayOfDeadlineSubsequentParts[1]);
            newDeadline.setDone(isDone);
            return newDeadline;
        case "E":
            String[] arrayOfEventSubsequentParts = arrayOfTask[2].split("[|\\-]");
            Event newEvent = new Event(arrayOfEventSubsequentParts[0], arrayOfEventSubsequentParts[1],
                    arrayOfEventSubsequentParts[2]);
            newEvent.setDone(isDone);
            return newEvent;
        default:
            return null;
        }
    }

    /**
     * Loads tasks from file.
     *
     * @return The array containing tasks in list format.
     */
    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Path filePath = Paths.get(FILEPATH);

        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                tasks.addAll(revertTasksToListFormat(lines));
            }
        } catch (IOException e) {
            System.out.println(Messages.ERROR_READING_FILE_MESSAGE + e.getMessage());
        }

        return tasks;
    }
}

