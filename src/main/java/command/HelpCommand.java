package command;

import exception.ZukeException;
import task.TaskList;
import ui.ResponseManager;

public class HelpCommand implements Command {

    /**
     * {@inheritDoc}
     *
     * Sends a help message to the user.
     *
     * @param tasks the task list.
     * @throws ZukeException if the command is invalid.
     */
    @Override
    public void run(TaskList tasks) throws ZukeException {
        ResponseManager.sendHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
