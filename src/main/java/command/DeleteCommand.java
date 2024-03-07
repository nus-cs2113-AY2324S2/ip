package command;

import exception.InputException;
import task.TaskList;
import tool.ResponseManager;

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
     * @throws InputException if the index is out of range.
     */
    @Override
    public void run(TaskList tasks) throws InputException {
        if (tasks.getSize() == 0) {
            throw new InputException(ResponseManager.DELETE_EMPTY_LIST_MSG);
        }
        if (taskIndex > tasks.getSize() || taskIndex <= 0) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
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
