package joe.command;

import joe.task.TaskManager;

/**
 * Command interface with two method signatures that all command type classes implement
 */
public interface Command {
    void executeCommand(TaskManager taskManager);

    /**
     * Returns a boolean indicating whether a command is an exit command
     *
     * @return true if ByeCommand, false otherwise
     */
    boolean isExit();
}
