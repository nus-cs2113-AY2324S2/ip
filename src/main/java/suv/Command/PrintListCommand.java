package suv.Command;

import suv.Helpers.Ui;

/**
 * The PrintListCommand class represents a command to print the list of tasks.
 * It does not require any input and simply prints the list of tasks to the user interface.
 */
public class PrintListCommand {

    /**
     * Constructs a new PrintListCommand object and prints the list of tasks.
     *
     * @throws SuvException If there is an error while executing the print list command.
     */
    public PrintListCommand() throws SuvException {
        Ui.printList();
    }
}
