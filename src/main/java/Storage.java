import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class to read and write to the local .txt file.
 *
 * @param File_PATH Relative file path to the local database.
 */
public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Returns the tasks read from the local .txt file.
     *
     * @return Task List.
     * @throws Exception Exceptions passed from getting the task.
     */
    public ArrayList<Task> readDb() throws Exception {
        // Reads from the local txt file to obtain tasks
        FileReader fileReader = new FileReader(FILE_PATH);
        try {
            return fileReader.getTasks();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Writes the task list to the local .txt file using file write.
     *
     * @param taskList Task List.
     */
    public void saveList(ArrayList<Task> taskList) {
        // Calls the FileWrite class to sae task list
        FileWrite fw = new FileWrite(FILE_PATH, taskList);
        try {
            fw.writeToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
