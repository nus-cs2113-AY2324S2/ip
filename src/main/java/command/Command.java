package command;
import exception.AdamException;
import task.TaskList;

/**
 * The Command interface represents a command to be executed.
 */
public interface Command {
    
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @return False if the program should continue running, true if the program should terminate.
     * @throws AdamException If an error occurs during the execution of the command.
     */
    boolean execute(TaskList tasks) throws AdamException;
}
