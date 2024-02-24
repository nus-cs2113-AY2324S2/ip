package bossman.command;

import bossman.exceptions.commandexceptions.InvalidDeleteCommandException;
import bossman.task.TaskList;

/**
 * DeleteCommand represents the command to delete a task.
 * It implements the Command interface.
 */
public class DeleteCommand implements Command{
    private TaskList userTasks;
    private int taskId; // ID of the task to be deleted

    /**
     * Constructs a DeleteCommand with the provided TaskList and command arguments.
     *
     * @param userTasks the TaskList containing user's tasks
     * @param commandArgs the arguments provided with the command
     * @throws InvalidDeleteCommandException if the command arguments are invalid
     */
    public DeleteCommand(TaskList userTasks, String commandArgs) throws InvalidDeleteCommandException{
        this.userTasks = userTasks;
        try {
            taskId = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            throw new InvalidDeleteCommandException("Out of range");
        }
    }

    /**
     * Executes the DeleteCommand by removing the task from the TaskList.
     */
    @Override
    public void execute() {
        userTasks.removeTask(taskId);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true, indicating that this command is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
