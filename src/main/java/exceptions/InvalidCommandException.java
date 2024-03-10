package exceptions;

import java.util.Arrays;

/**
 * Represents the exception thrown when the user does not input a valid command word.
 */
public class InvalidCommandException extends WallybotException {
    /**
     * Constructs InvalidCommandException.
     */
    public InvalidCommandException() {
        message = showCommands();
    }

    /**
     * Creates a message that shows all valid commands to help the user.
     */
    public String showCommands() {
        String[] commands = {"bye", "todo", "deadline", "event", "list", "mark", "unmark", "delete", "find"};
        return "Need some help? Here are all my valid commands:" +
                System.lineSeparator() +
                Arrays.toString(commands);
    }
}
