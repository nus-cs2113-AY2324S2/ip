package command;

import exception.InputException;
import task.TaskList;
import tool.ResponseManager;

public class HelpCommand implements Command {
    @Override
    public void run(TaskList tasks) throws InputException {
        ResponseManager.sendHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
