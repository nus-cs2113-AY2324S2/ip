package joe.command;

import joe.task.TaskManager;
import joe.util.Printer;

public class ListCommand implements Command {
    protected String arguments;

    public ListCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void executeCommand(TaskManager taskManager) {
        if (!arguments.isEmpty()) {
            Printer.printListError();
            return;
        }
        taskManager.listTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
