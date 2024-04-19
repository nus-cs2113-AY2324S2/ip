package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

/**
 * Abstract class representing a Command.
 */
abstract public class Command {
    protected final TaskManager taskManager;

    /**
     * Abstract constructor for a Command Object.
     *
     * @param taskManager Current TaskManager instance.
     */
    protected Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the current Command, and returns the execution result as a String.
     *
     * @return Result String of the executed Command.
     * @throws InvalidTaskNumberException If an invalid task number is provided to the Command.
     */
    abstract public String executeCommand() throws InvalidTaskNumberException;

    /**
     * Checks if the current Command is an Exit Command (i.e. Bye)
     * Returns true if the current Command is an Exit Command (i.e. Bye).
     * Else, returns false.
     *
     * @return Boolean value denoting if the Command is an Exit Command.
     */
    public boolean isExitCommand() {
        return false;
    }
}
