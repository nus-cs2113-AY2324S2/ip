import java.io.File;
import java.io.FileNotFoundException;


// return new TasksList if no tasks list is found in file
public class FileManager implements FileInterface {
    public FileManager() {}
    public static TasksList getTasksList() throws FileNotFoundException {
        File f = new File(TASK_LIST_FILE);
        System.out.println("full path: " + f.getAbsolutePath());
        if (f.exists()) {
            System.out.println("Existing file found, importing content...");
            return new TasksListReader().parse(f);
        } else {
            // TasksList file does not exist
            System.out.println("Previous Task List  not found, creating a new one!");
            return new TasksList();
        }
    }
}
