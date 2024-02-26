package natsu.command;

import static natsu.util.Ui.printExitMessage;

/**
 * Represents a command to terminate the application.
 * This command triggers the application's exit sequence, displaying a farewell
 * message to the user.
 */
public class ByeCommand {

    /**
     * Constructs a {@code ByeCommand} instance which immediately triggers the
     * printing of the application's exit message upon instantiation.
     */
    public ByeCommand() {
        printExitMessage();
    }
}
