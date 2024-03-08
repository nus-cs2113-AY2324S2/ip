package nick.parser;

import nick.NickException;
import nick.command.*;

/**
 * Creates a parser object which parse the input command from the user.
 * Subsequently, it returns the corresponding command object.
 */
public class Parser {

    /**
     * Parse the input command from the user and returns the corresponding command object.
     *
     * @param fullCommand Input command from user.
     * @return Corresponding command object.
     * @throws NickException Exception object.
     */
    public static Command parse(String fullCommand) throws NickException {
        String[] commands = fullCommand.split(" ", 2);
        String command = commands[0];
        String arguments = (commands.length == 2) ? commands[1] : null;

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo":
            return new TodoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        default:
            throw new NickException();
        }
    }
}
