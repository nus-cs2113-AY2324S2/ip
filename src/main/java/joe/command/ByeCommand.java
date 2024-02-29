package joe.command;

import joe.task.TaskManager;
import joe.util.Printer;

/**
 * Handles the exiting of the program, implements the Command interface
 */
public class ByeCommand implements Command {
    protected String arguments;
    protected boolean isValidExit;

    public ByeCommand(String arguments) {
        this.arguments = arguments;
        this.isValidExit = arguments.isEmpty();
    }

    /**
     * Checks whether the instance is a valid ByeCommand. Sets isValidExit to be false if it is not.
     *
     * @param taskManager placeholder parameter implemented from the method signature of the Command interface
     */
    @Override
    public void executeCommand(TaskManager taskManager) {
        if (!arguments.isEmpty()) {
            Printer.printExitError();
        }
    }

    /**
     * Returns true if instance a valid exit command
     *
     * @return true if isValidExit is true
     */
    @Override
    public boolean isExit() {
        return isValidExit;
    }
}
