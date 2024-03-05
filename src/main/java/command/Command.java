package command;

import exception.InputException;
import task.TaskList;

public interface Command {
    /**
     * This method runs the command
     * @param tasks the task list
     * @throws InputException if the command is invalid
     */
    public void run(TaskList tasks) throws InputException;
    
    /**
     * This method checks if the command is an exit command
     * @return true if the command is an exit command, false otherwise
     */
    public boolean isExit();
}
