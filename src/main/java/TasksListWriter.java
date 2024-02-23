import java.io.FileWriter;
import java.io.IOException;

public class TasksListWriter implements FIleManager {
    private static void writeTasksListToFile(String filePath, TasksList tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasksList.toString()); //toString to be made?
        fw.close();
    }
}
