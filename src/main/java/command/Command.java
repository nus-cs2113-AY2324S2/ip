package command;

import exception.ZukeException;
import task.TaskList;

/**
 * The Command interface represents a command that can be executed by ZukeBot.
 */
public interface Command {
    /**
     * Runs the command.
     *
     * @param tasks the task list.
     * @throws ZukeException if the command is invalid.
     */
    public void run(TaskList tasks) throws ZukeException;
    
    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit();
}
