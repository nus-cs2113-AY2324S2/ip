package jeff.commands;

import jeff.Command;
import jeff.Printer;

public class ListCommand extends Command {
    @Override
    public void execute() {
        Printer.printTasks();
    }
}
