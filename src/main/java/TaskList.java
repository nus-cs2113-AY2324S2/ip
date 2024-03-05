import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList (ArrayList<Task> tasks ) {
        this.tasks = tasks;
    }

    /**Returns the list of tasks */
    public ArrayList<Task> getTasks () {
        return this.tasks;
    }
}
