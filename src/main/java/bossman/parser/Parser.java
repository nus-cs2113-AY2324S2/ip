package bossman.parser;

import bossman.command.*;
import bossman.exceptions.BossManExceptions;
import bossman.exceptions.commandexceptions.UnknownCommandException;
import bossman.task.TaskList;

/**
 * Parser class is responsible for parsing user input and determining the corresponding command.
 * It maps user input to the appropriate command object based on the command keyword.
 */
public class Parser {

    /**
     * Determines the command based on user input and returns the corresponding command object.
     *
     * @param userTasks the TaskList object containing user's tasks
     * @param userInput the input string provided by the user
     * @return the command object corresponding to the user input
     * @throws BossManExceptions if an error occurs while determining the command
     */
    public static Command determineCommand(TaskList userTasks, String userInput) throws BossManExceptions {
        String[] userWords = userInput.trim().split("\\s+", 2);
        String userCommand = userWords[0];
        String commandArgs = userWords.length == 2 ? userWords[1] : "";
        switch(userCommand) {
        case "list":
            return new ListCommand(userTasks);

        case "todo":
            return new ToDoCommand(userTasks, commandArgs);

        case "deadline":
            return new DeadlineCommand(userTasks, commandArgs);

        case "event":
            return new EventCommand(userTasks, commandArgs);

        case "mark":
            return new MarkCommand(userTasks, commandArgs);

        case "unmark":
            return new UnmarkCommand(userTasks, commandArgs);

        case "delete":
            return new DeleteCommand(userTasks, commandArgs);

        case "find":
            return new FindCommand(userTasks, commandArgs);

        case "bye":
            return new ByeCommand(commandArgs);

        default:
            throw new UnknownCommandException("Unknown command");
        }
    }
}