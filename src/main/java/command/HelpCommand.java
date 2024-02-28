package carrot.command;

import carrot.command.Command;
import carrot.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(String userInput) {
        Ui.printHelpCommand();
    }
}
