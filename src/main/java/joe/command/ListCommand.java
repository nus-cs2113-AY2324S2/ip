package joe.command;

import joe.task.TaskManager;
import joe.util.Printer;

/**
 * Command indicating a list command, implements the Command interface
 */
public class ListCommand implements Command {
    protected String arguments;

    public ListCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the list command and lists all tasks accordingly
     *
     * @param taskManager the TaskManager instance used by Joe
     */
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
