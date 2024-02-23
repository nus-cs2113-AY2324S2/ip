import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
    static void writeToFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        taskList.forEach(task -> {
            int markDoneOrUndone = 0;
            if (task.getStatusIcon().equals("X")) {
                markDoneOrUndone = 1;
            }
            try {
                fw.write(task.getTaskTypeIcon() + " | " + markDoneOrUndone + " | " + task.getDescription() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fw.close();
    }
}
