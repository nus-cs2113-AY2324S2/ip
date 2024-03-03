import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    public ArrayList<Task> readDb() throws Exception {
        // Reads from the local txt file to obtain tasks
        FileReader fileReader = new FileReader(FILE_PATH);
        try {
            return fileReader.getTasks();
        } catch (Exception e) {
            throw e;
        }
    }

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
