package gary.command;

import gary.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public void handleCommand() {
        Ui.exitProgramme();
    }
}
