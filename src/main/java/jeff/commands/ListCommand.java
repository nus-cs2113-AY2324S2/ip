package jeff.commands;

import jeff.Command;
import jeff.Printer;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by printing all tasks in the task list.
     */
    @Override
    public void execute() {
        Printer.printTasks();
    }
}
