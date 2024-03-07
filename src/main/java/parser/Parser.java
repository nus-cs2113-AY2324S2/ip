package parser;

import command.Command;
import command.FindCommand;
import command.TodoCommand;
import command.EventCommand;
import command.DeadlineCommand;
import command.ListCommand;
import command.DeleteCommand;
import command.ByeCommand;
import command.MarkCommand;
import command.UnmarkCommand;

import commandexceptions.JingHaoExceptions;

/**
 * Parser class determines the type of command based on user's input and
 * maps it to the corresponding command with the required task descriptions
 * given by the user.
 */
public class Parser {

    /**
     * Determines the command based on user input and returns the corresponding command object.
     * @param userInput the input string provided by the user.
     * @return Type of command object to be executed.
     * @throws JingHaoExceptions If the type of command is not part of the list of commands.
     */
    public static Command parse(String userInput) throws JingHaoExceptions {

        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();
        String description = (words.length == 2) ? words[1] : "";
        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(description);
        case "unmark":
            return new UnmarkCommand(description);
        case "todo":
            return new TodoCommand(description);
        case "deadline":
            return new DeadlineCommand(description);
        case "event":
            return new EventCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindCommand(description);
        default:
            throw new JingHaoExceptions("Unknown command encountered! Please try again.");
        }
    }

}
