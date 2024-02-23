import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// return new TasksList if no tasks list is found in file
public class TasksListFromFile implements FIleManager{
    public static TasksList getTasksList() {
        File f = new File(TASK_LIST_FILE);
        if (f.exists()) {
            return new TasksListReader().parse();
        } else {
            // TasksList file does not exist
            System.out.println("Previous Task List  not found, creating a new one!");
            return new TasksList();
        }
    }
}
