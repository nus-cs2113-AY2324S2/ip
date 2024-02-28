package carrot.command;

import carrot.command.Command;
import carrot.ui.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(String userInput) {
        Ui.printInvalidCommand();
    }
}
