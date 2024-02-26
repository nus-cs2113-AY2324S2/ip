package joe.command;

import joe.task.TaskManager;
import joe.util.Printer;

public class InvalidCommand implements Command {
    @Override
    public void executeCommand(TaskManager taskManager) {
        Printer.printDefaultError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
