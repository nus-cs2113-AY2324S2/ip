package carrot.command;

import carrot.command.Command;
import carrot.ui.Ui;

/**
 * Represents a command to handle invalid user input.
 * Extends the {@link Command} abstract class.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command, printing an error message for invalid user input.
     *
     * @param userInput the invalid user input string (not used in this command)
     */
    @Override
    public void execute(String userInput) {
        Ui.printInvalidCommand();
    }
}
