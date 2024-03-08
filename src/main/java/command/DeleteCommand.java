package command;

import exception.ZukeException;
import task.TaskList;
import ui.ResponseManager;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private static final CommandType taskType = CommandType.DELETE;
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     * 
     * Deletes a task from the task list and prints the delete action to the user.
     *
     * @param tasks the task list.
     * @throws ZukeException if the index is out of range.
     */
    @Override
    public void run(TaskList tasks) throws ZukeException {
        if (tasks.getSize() == 0) {
            throw new ZukeException(ResponseManager.DELETE_EMPTY_LIST_MSG);
        }
        if (taskIndex > tasks.getSize() || taskIndex <= 0) {
            throw new ZukeException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        String response = tasks.getPosAt(taskIndex).toString();
        tasks.deleteTaskAt(taskIndex);
        response = response + "\n" + tasks;
        ResponseManager.printActionOnTasks(taskType, response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
