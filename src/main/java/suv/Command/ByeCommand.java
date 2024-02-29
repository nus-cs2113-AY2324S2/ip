package suv.Command;

import suv.Helpers.Ui;

/**
 * The ByeCommand class represents a command to exit the application.
 * It prints a farewell message to the user.
 */
public class ByeCommand {

    /**
     * Constructs a new ByeCommand object and prints a farewell message to the user.
     *
     * @throws SuvException If there is an error while executing the bye command.
     */
    public ByeCommand() throws SuvException{
        Ui.printByeMessage();
    }
}
