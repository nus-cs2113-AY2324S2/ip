package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

/**
 * Abstract class representing a Command.
 */
abstract public class Command {
    protected final TaskManager taskManager;

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the current Command.
     *
     * @return Result String of the executed Command.
     * @throws InvalidTaskNumberException If an invalid task number is provided to the Command.
     */
    abstract public String executeCommand() throws InvalidTaskNumberException;

    /**
     * Checks if the current Command is an Exit Command (i.e. Bye)
     *
     * @return Boolean value denoting if the Command is an Exit Command.
     */
    public boolean isExitCommand() {
        return false;
    }
}
