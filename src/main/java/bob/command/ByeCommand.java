package bob.command;

import bob.utils.TaskManager;

/**
 * Class representing a "bye" command.
 */
public class ByeCommand extends Command {
    /**
     * Creates a ByeCommand Object.
     * @param taskManager Current TaskManager instance.
     */
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
