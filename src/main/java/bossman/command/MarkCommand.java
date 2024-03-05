package bossman.command;

import bossman.exceptions.commandexceptions.InvalidMarkCommandException;
import bossman.task.TaskList;

/**
 * MarkCommand represents the command to mark a task as done.
 * It implements the Command interface.
 */
public class MarkCommand implements Command {
    private TaskList userTasks;
    private int taskId; // ID of the task to be marked as done

    /**
     * Constructs a MarkCommand with the provided TaskList and command arguments.
     *
     * @param userTasks the TaskList containing user's tasks
     * @param commandArgs the arguments provided with the command
     * @throws InvalidMarkCommandException if the command arguments are invalid
     */
    public MarkCommand(TaskList userTasks, String commandArgs) throws InvalidMarkCommandException {
        this.userTasks = userTasks;

        try {
            taskId = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            throw new InvalidMarkCommandException("Out of range");
        }
    }

    /**
     * Executes the MarkCommand by marking the task as done in the TaskList.
     */
    @Override
    public void execute() {
        userTasks.markTask(taskId);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, indicating that this command is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
