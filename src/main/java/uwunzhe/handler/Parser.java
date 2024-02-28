package uwunzhe.handler;

import uwunzhe.exceptions.UwunzheException;
import uwunzhe.exceptions.ExceptionMessages;
import uwunzhe.commands.Command;
import uwunzhe.commands.ListCommand;
import uwunzhe.commands.MarkUnmarkCommand;
import uwunzhe.commands.AddCommand;
import uwunzhe.commands.DeleteCommand;
import uwunzhe.commands.FindCommand;
import uwunzhe.commands.DateCommand;
import uwunzhe.commands.ExitCommand;

public class Parser {
    /**
     * Parses the input and calls the appropriate function.
     * 
     * @param input The string input from the user.
     * @return Object of type {@link Command} containing details of command to be executed.
     * @throws UwunzheException If the command is invalid.
     */
    public Command parseInput(String input) throws UwunzheException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0].toLowerCase();
        String taskString = splitInput.length > 1 ? splitInput[1] : "";
        
        return parseCommand(command, taskString);
    }

    /**
     * Parses the command and calls the appropriate function.
     * 
     * @param command The string command from the user.
     * @param taskString The string containing details of the task to be executed.
     * @return Object of type {@link Command} containing details of command to be executed.
     * @throws UwunzheException If the command is invalid.
     */
    public Command parseCommand (String command, String taskString)
            throws UwunzheException {
        switch (command) {
        case "list":
            return new ListCommand(command, taskString);

        case "mark":
        // Fallthrough
        case "unmark":
            return new MarkUnmarkCommand(command, taskString);

        case "todo":
        // Fallthrough
        case "deadline":
        // Fallthrough
        case "event":
            return new AddCommand(command, taskString);

        case "delete":
            return new DeleteCommand(command, taskString);

        case "find":
            return new FindCommand(command, taskString);

        case "date":
            return new DateCommand(command, taskString);

        case "bye":
            return new ExitCommand(command, taskString);

        default:
            // Add message for invalid input
            throw new UwunzheException(ExceptionMessages.INVALID_COMMAND);
        }
    }
}
