import Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> Tasks;

    public TaskList() throws IOException {
        this.Tasks = DataHandler.readFileContents(DataHandler.FILE_PATH);
    }
}