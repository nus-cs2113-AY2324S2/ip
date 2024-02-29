package gary.command;

import gary.task.Task;
import gary.task.TaskList;

import java.util.ArrayList;

/**
 * ListCommand class is used to handle users' command to list all the available tasks that
 * have been added previously.
 */
public class ListCommand extends Command {
    private ArrayList<Task> todos;

    /**
     * Constructor for ListCommand to take in the array list of tasks available.
     *
     * @param todos array list that stores and manages the task while programme is running.
     */
    public ListCommand(ArrayList<Task> todos) {
        this.todos = todos;
    }

    public void handleCommand() {
        int todosCount = this.todos.size();
        TaskList.processList(todosCount, this.todos);
    }
}
