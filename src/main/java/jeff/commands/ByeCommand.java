package jeff.commands;

import jeff.Command;
import jeff.Printer;

public class ByeCommand extends Command {
    @Override
    public void execute() {
        Printer.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
