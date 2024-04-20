import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that writes the tasks from a list to local storage.
 *
 * @param path Relative file path of the file.
 * @param tasks List of tasks loaded.
 */
public class FileWrite {
    protected String filePath;
    protected ArrayList<Task> tasks;

    public FileWrite(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    /**
     * Loops through the list and writes to a .txt file in storage.
     *
     * @throws IOException If it cannot be written.
     */
    public void writeToFile() throws IOException {
        // Writes the task list to file to store in local files
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.getTaskType()
                    + "|" + (task.isDone ? 1 : 0)
                    + "|" + task.task
                    + (task.getStart() == null ? "" : "|" + task.getStart())
                    + (task.getEnd() == null ? "" : "|" + task.getEnd()) + System.lineSeparator());
        }
        fw.close();
    }
}
