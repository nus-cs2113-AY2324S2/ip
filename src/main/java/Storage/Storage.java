package Storage;

import TaskList.TaskList;
import tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a utility class for storing and loading tasks to/from a file.
 * This class provides methods to load tasks from a file and upload tasks to a file.
 */
public class Storage {

    private static String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     * @param filepath The path to the file for storing and loading tasks
     */
    public Storage(String filepath){
        Storage.filepath = filepath;
    }

    /**
     * Loads tasks from the specified file in the filepath.
     * @param filepath The path to the file from which tasks will be loaded
     * @return The ArrayList of tasks loaded from the file
     * @throws FileNotFoundException If the specified file is not found
     */
    public static ArrayList<Task> load(String filepath) throws FileNotFoundException {
        return LoadData.loadData(filepath);
    }

    /**
     * Appends tasks to the specified file.
     * @param filepath The path to the file to which tasks will be uploaded
     * @param tasks The list of tasks to upload to the file
     * @throws IOException If an I/O error occurs while uploading tasks to the file
     */
    public static void upload(String filepath, TaskList tasks) throws IOException {
         UploadData.updateFile(filepath, tasks);
    }
}
