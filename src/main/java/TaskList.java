import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addToList (Task task) {
        taskList.add(task);
    }

    public int getTaskCount () {
        return taskList.size();
    }
}
