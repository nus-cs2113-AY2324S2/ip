package carrot.command;

import carrot.command.Command;
import carrot.ui.Ui;

/**
 * Represents a command to display help information.
 * Extends the {@link Command} abstract class.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command, printing the available commands and their syntax.
     *
     * @param userInput the user input string (not used in this command)
     */
    @Override
    public void execute(String userInput) {
        Ui.printHelpCommand();
    }
}
