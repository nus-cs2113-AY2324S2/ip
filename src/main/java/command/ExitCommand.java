package carrot.command;

import carrot.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(String userInput) {
        Ui.getScanner().close();
        Ui.sayGoodbye();
        System.exit(0);
    }
}
