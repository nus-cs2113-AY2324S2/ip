package command;

import task.TaskList;
import tool.ResponseManager;

public class ListCommand implements Command {
    public void run(TaskList tasks) {
        ResponseManager.listTaskToUser(tasks.listTasks());
    }

    public boolean isExit() {
        return false;
    }
}
