package uwunzhe.handler;

import uwunzhe.exceptions.UwunzheException;
import uwunzhe.util.TaskList;
import uwunzhe.commands.Command;
import uwunzhe.commands.ListCommand;
import uwunzhe.commands.MarkUnmarkCommand;
import uwunzhe.commands.AddCommand;
import uwunzhe.commands.DeleteCommand;
import uwunzhe.commands.ExitCommand;

public class Parser {
    /**
     * Parses the input and calls the appropriate function.
     * 
     * @param input The input from the user.
     * @return If the task list was updated.
     * @throws UwunzheException
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
     * @param command The command from the user.
     * @param taskString The task from the user.
     * @return Object containing details of command to be executed.
     * @throws UwunzheException
     */
    public Command parseCommand (String command, String taskString)
            throws UwunzheException {
        switch (command) {
        case "list":
            return new ListCommand(command, "");

        case "mark":
        case "unmark":
            return new MarkUnmarkCommand(command, taskString);

        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(command, taskString);

        case "delete":
            return new DeleteCommand(command, taskString);

        case "bye":
            return new ExitCommand(command, "");

        default:
            // Add message for invalid input
            throw new UwunzheException("OH NO! I cannot understand!");
        }
    }
}
