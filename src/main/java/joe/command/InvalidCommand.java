package joe.command;

import joe.task.TaskManager;
import joe.util.Printer;

/**
 * An invalid command, implements the Command interface
 */
public class InvalidCommand implements Command {
    /**
     * Prints the defaults error message
     * @param taskManager placeholder parameter implemented from the method signature of the Command interface
     */
    @Override
    public void executeCommand(TaskManager taskManager) {
        Printer.printDefaultError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
