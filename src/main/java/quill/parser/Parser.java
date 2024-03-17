package quill.parser;

import quill.command.*;

/**
 * The Parser Class handles the parsing of user input into valid commands.
 */
public class Parser {

    /**
     * Parses the user input and returns an appropriate Command object.
     *
     * @param input The user input.
     * @return A Command object corresponding to the parsed command.
     */
    public static Command parse(String input) {
        String command;
        int index = input.indexOf(" ");
        if (index != -1) {
            command = input.substring(0, index);
            input = input.substring(index + 1);
        } else {
            command = input;
            input = "";
        }
        Command c;
        switch(command) {
        case "bye":
            c = new ExitCommand(command, input);
            break;
        case "list":
            c = new ListCommand(command, input);
            break;
        case "mark":
        case "unmark":
            c = new MarkCommand(command, input);
            break;
        case "todo":
        case "deadline":
        case "event":
            c = new AddCommand(command, input);
            break;
        case "delete":
            c = new DeleteCommand(command, input);
            break;
        case "find":
            c = new FindCommand(command, input);
            break;
        default:
            c= new UnknownCommand(command, input);
            break;
        }
        return c;
    }

}
