package gary.command;

import gary.ui.Ui;

/**
 * ExitCommand class is called to exit the programme.
 */
public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public void handleCommand() {
        Ui.exitProgramme();
    }
}
