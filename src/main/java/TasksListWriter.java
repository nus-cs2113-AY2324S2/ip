import java.io.FileWriter;
import java.io.IOException;

public class TasksListWriter implements FileInterface {
    public static void writeTasksListToFile(TasksList tasksList) throws IOException {
        FileWriter fw = new FileWriter(TASK_LIST_FILE);
        fw.write(tasksList.toString()); //toString to be made?
        fw.close();
    }
}
