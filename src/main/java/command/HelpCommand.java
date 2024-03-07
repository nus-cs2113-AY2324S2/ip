package command;

import exception.InputException;
import task.TaskList;
import tool.ResponseManager;

public class HelpCommand implements Command {

    /**
     * {@inheritDoc}
     *
     * Sends a help message to the user.
     *
     * @param tasks the task list.
     * @throws InputException if the command is invalid.
     */
    @Override
    public void run(TaskList tasks) throws InputException {
        ResponseManager.sendHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
