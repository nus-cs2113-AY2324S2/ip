package command;

import task.TaskList;
import ui.ResponseManager;

/**
 * The ListCommand class represents a command to list all the tasks in the task list.
 */
public class ListCommand implements Command {
    /**
     * {@inheritDoc}
     * 
     * Lists all the tasks in the task list to the user,
     * or sends an empty list message if the task list is empty.
     *
     * @param tasks the task list.
     */
    @Override
    public void run(TaskList tasks) {
        if (tasks.getSize() == 0) {
            ResponseManager.sendEmptyListMsg();
        } else {
            ResponseManager.listTaskToUser(tasks.listTasks());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
