package schmidt.storage;

import schmidt.exception.SchmidtException;
import schmidt.parser.Parser;
import schmidt.task.Task;
import schmidt.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of tasks in a file to be loaded from and saved to.
 */
public class Storage {
    public static final String REGEX_DELIMITER = " \\| ";
    public static final String DELIMITER = " | ";
    public static final int TASK_TYPE_INDEX = 0;
    public static final int IS_DONE_INDEX = 1;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int BY_INDEX = 3;
    public static final int FROM_INDEX = 4;
    public static final int TO_INDEX = 5;
    public static final String CORRUPTED_STORAGE_MESSAGE = "Saved tasks are corrupted\n" + "Starting with a new task list";
    public static final String STORAGE_FILE_NOT_FOUND_ERROR_MESSAGE = "Storage file not found\n" + "Starting with an empty task list";
    public static final String SAVING_TASKS_ERROR_MESSAGE = "An error occurred while saving tasks";
    private final File file;

    /**
     * Constructs a storage with the file path.
     *
     * @param filePath the file path.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return the list of tasks.
     * @throws SchmidtException if there was an error loading tasks.
     */
    public ArrayList<Task> load() throws SchmidtException {
        try {
            ArrayList<Task> tasks;
            tasks = new ArrayList<>();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(Parser.parseStorageToTask(line));
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new SchmidtException(STORAGE_FILE_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks the list of tasks.
     * @throws SchmidtException if there was an error saving tasks.
     */
    public void save(TaskList tasks) throws SchmidtException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);

                fileWriter.write(Parser.parseTaskToStorage(task));
            }
            fileWriter.close();
        } catch (Exception e) {
            throw new SchmidtException(SAVING_TASKS_ERROR_MESSAGE);
        }
    }
}
