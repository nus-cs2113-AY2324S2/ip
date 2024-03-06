package command;

import task.TaskList;
import tool.ResponseManager;

public class ListCommand implements Command {
    /**
     * {@inheritDoc}
     * 
     * Lists all the tasks in the task list to the user
     * @param tasks the task list
     */
    @Override
    public void run(TaskList tasks) {
        ResponseManager.listTaskToUser(tasks.listTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
