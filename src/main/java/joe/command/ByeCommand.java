package joe.command;

import joe.task.TaskManager;
import joe.util.Printer;

public class ByeCommand implements Command {
    protected String arguments;
    protected boolean isValidExit;

    public ByeCommand(String arguments) {
        this.arguments = arguments;
        this.isValidExit = arguments.isEmpty();
    }
    @Override
    public void executeCommand(TaskManager taskManager) {
        if (!arguments.isEmpty()) {
            Printer.printExitError();
        }
    }

    @Override
    public boolean isExit() {
        return isValidExit;
    }
}
