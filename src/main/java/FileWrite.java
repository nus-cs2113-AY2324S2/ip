import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWrite {
    protected String filePath;
    protected ArrayList<Task> tasks;

    public FileWrite(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.getTaskType()
                    + "|" + (task.isDone ? 1 : 0)
                    + "|" + task.task
                    + (task.getStart().isBlank() ? "" : "|" + task.getStart())
                    + (task.getEnd().isBlank() ? "" : "|" + task.getEnd()) + System.lineSeparator());
        }
        fw.close();
    }
}
