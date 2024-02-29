package carrot.command;

import carrot.ui.Ui;

/**
 * Represents a command to exit the application.
 * Extends the {@link Command} abstract class.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, closing the scanner, printing a goodbye message,
     * and terminating the application.
     *
     * @param userInput the user input string (not used in this command)
     */
    @Override
    public void execute(String userInput) {
        Ui.getScanner().close();
        Ui.sayGoodbye();
        System.exit(0);
    }
}
