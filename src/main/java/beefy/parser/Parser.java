package beefy.parser;

import beefy.BeefyException;
import beefy.command.Command;
import beefy.command.*;
import beefy.task.TaskList;

/**
 * Represents the input parser of Beefy chatbot.
 * Parses user input into command and details of the command.
 * Generates the respective Command objects based on the parsed command keyword.
 */
public class Parser {
    /**
     * Parses user input into command and details, and creates the corresponding Command object.
     *
     * @param userTasks list of tasks added by User.
     * @param userInput User input to be parsed.
     * @return Command object to be executed.
     * @throws BeefyException If the user input is not part of the command list.
     */
    public static Command determineCommand(TaskList userTasks, String userInput) throws BeefyException {
        String[] userWords = userInput.trim().split("\\s+", 2);
        String userCommand = userWords[0];
        String userParams = userWords.length == 2 ? userWords[1] : "";
        switch(userCommand) {
        case "bye":
            return new ByeCommand(userParams);
        case "list":
            return new ListCommand(userTasks, userParams);
        case "todo":
            return new ToDoCommand(userTasks, userParams);
        case "deadline":
            return new DeadlineCommand(userTasks, userParams);
        case "event":
            return new EventCommand(userTasks, userParams);
        case "mark":
            return new MarkCommand(userTasks, userParams);
        case "unmark":
            return new UnmarkCommand(userTasks, userParams);
        case "delete":
            return new DeleteCommand(userTasks, userParams);
        case "find":
            return new FindCommand(userTasks, userParams);
        default:
            throw new BeefyException("What Did you Say? I do not understand this command.");
        }
    }
}
