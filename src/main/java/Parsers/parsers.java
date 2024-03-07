package Parsers;

import Commands.*;
import Storage.Store;

public class parsers {

    private Store store; // Add a reference to the Store

    /**
     * Constructs a parser with a reference to the Store.
     *
     * @param store The Store object used for data persistence, passed to commands that require it.
     */
    public parsers(Store store) {
        this.store = store; // Initialize the Store reference
    }

    /**
     * Parses a string description of a command into an executable Command object.
     * This method determines the type of command based on the input string,
     * initializes the command with necessary parameters, and returns the Command object.
     * Commands related to task modification or querying are provided with a Store
     * object to enable data persistence.
     *
     * @param description The full command string input by the user.
     * @param store The Store object for commands that require data persistence.
     * @return A Command object ready to be executed by the application.
     */

    public static Command parse(String description, Store store) {
        String command;
        Command commandObject;
        int index = description.indexOf(" ");
        String parameter = "";

        if (index != -1) {
            command = description.substring(0, index);
            parameter = description.substring(index + 1);
        } else {
            command = description;
        }

        switch (command) {
        case "todo":
        case "deadline":
        case "event":
            commandObject = new AddCommand(command, parameter, store); // Pass the store instance to AddCommand
            break;
        case "bye":
            commandObject = new ExitCommand(command, parameter, store);
            break;
        case "list":
            commandObject = new ListCommand(command, parameter);
            break;
        case "mark":
            commandObject = new MarkCommand(command, parameter,store);
            break;
        case "unmark":
            commandObject = new UnmarkCommand(command, parameter,store);
            break;
        case "delete":
            commandObject = new DeleteCommand(command, parameter,store);
            break;
        case "find":
            commandObject = new FindCommand(command, parameter);
            break;
        case "clearlist":
            commandObject = new ClearCommand(command, parameter,store);
            break;
        default:
            commandObject = new UnknownCommand(command, parameter);
            break;
        }
        return commandObject;
    }
}
