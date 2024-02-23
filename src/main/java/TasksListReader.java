import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TasksListReader implements FIleManager{
    public TasksList parse(String string) {
        File f = new File(TASK_LIST_FILE);
        Scanner scanner = new Scanner(f);

        
    }
}
