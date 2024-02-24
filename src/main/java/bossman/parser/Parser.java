package bossman.parser;

import bossman.command.*;
import bossman.exceptions.BossManExceptions;
import bossman.exceptions.commandexceptions.UnknownCommandException;
import bossman.task.TaskList;

public class Parser {
    public static Command determineCommand(TaskList userTasks, String userInput) throws BossManExceptions {
        String[] userWords = userInput.trim().split("\\s+", 2);
        String userCommand = userWords[0];
        String commandArgs = userWords.length == 2 ? userWords[1] : "";
        switch(userCommand) {
        case "bye":
            return new ByeCommand(commandArgs);

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

        default:
            throw new UnknownCommandException("Unknown command");
        }
    }
}