package bob.command;

import bob.utils.TaskManager;

public class ByeCommand extends Command {
    public ByeCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        return "";
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
