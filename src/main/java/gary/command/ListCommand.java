package gary.command;

import gary.task.Task;
import gary.task.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command {
    private ArrayList<Task> todos;
    public ListCommand(ArrayList<Task> todos) {
        this.todos = todos;
    }

    public void handleCommand() {
        int todosCount = this.todos.size();
        TaskList.processList(todosCount, this.todos);
    }
}
