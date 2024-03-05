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
     * This method deletes a task from the task list and prints the delete action to the user
     * @param tasks the task list
     * @throws InputException if the index is out of range
     */
    @Override
    public void run(TaskList tasks) throws InputException {
        String response = tasks.getPosAt(taskIndex).toString();
        tasks.deleteTaskAt(taskIndex);
        response = response + "\n" + tasks;
        ResponseManager.printActionOnTasks(taskType.getType(), response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
