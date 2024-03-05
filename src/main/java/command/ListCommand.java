package command;

import task.TaskList;
import tool.ResponseManager;

public class ListCommand implements Command {
    /**
     * {@inheritDoc}
     * 
     * This method lists all the tasks in the task list
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
