package bob.utils;

import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.TodoCommand;
import bob.command.UnmarkCommand;
import bob.exceptions.InvalidArgumentException;
import bob.exceptions.InvalidCommandException;
import bob.exceptions.InvalidTaskNumberException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.MatchResult;

public class Parser {
    public Command processUserCommand(String userCommand, TaskManager taskManager, Ui userInterface) throws
            InvalidTaskNumberException, InvalidArgumentException, InvalidCommandException {
            String[] arguments;

            switch (userCommand) {
            case "LIST":
                return new ListCommand(taskManager);
            case "MARK":
            case "UNMARK":
            case "DELETE":
                try {
                    arguments = parseArguments(userCommand, userInterface);
                    int taskId = Integer.parseInt(arguments[0]);
                    return getTaskUtilityCommand(taskManager, userCommand, taskId);
                } catch (NumberFormatException exception) {
                    throw new InvalidTaskNumberException(userCommand);
                }
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                arguments = parseArguments(userCommand, userInterface);
                return getTaskCreationCommand(taskManager, userCommand, arguments);
            case "BYE":
                return new ByeCommand(taskManager);
            default:
                throw new InvalidCommandException();
            }
    }

    private Command getTaskUtilityCommand(TaskManager taskManager, String userCommand, int taskId) {
        if (userCommand.equals("MARK")) {
            return new MarkCommand(taskManager, taskId);
        } else if (userCommand.equals("UNMARK")) {
            return new UnmarkCommand(taskManager, taskId);
        } else {
            return new DeleteCommand(taskManager, taskId);
        }
    }

    private Command getTaskCreationCommand(TaskManager taskManager, String userCommand, String[] arguments) {
        String taskName = arguments[0];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (userCommand.equals("TODO")) {
            return new TodoCommand(taskManager, taskName);
        } else if (userCommand.equals("DEADLINE")) {
            String dueDate = arguments[1];
            LocalDateTime dueDateTime = LocalDateTime.parse(dueDate, dateTimeFormatter);
            return new DeadlineCommand(taskManager, taskName, dueDateTime);
        } else {
            String startDate = arguments[1];
            String endDate = arguments[2];
            LocalDateTime startDateTime = LocalDateTime.parse(startDate, dateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endDate, dateTimeFormatter);
            return new EventCommand(taskManager, taskName, startDateTime, endDateTime);
        }
    }

    /**
     * Parse arguments provided (e.g., /from, /by, /to)
     */
    private String[] parseArguments(String userCommand, Ui userInterface) throws InvalidArgumentException {
        int expectedArgumentCount;

        // Set regex format based on command type
        switch (userCommand) {
        case "DEADLINE":
            userInterface.findInLine("(.+) /by (.+)");
            expectedArgumentCount = 2;
            break;
        case "EVENT":
            userInterface.findInLine("(.+) /from (.+) /to (.+)");
            expectedArgumentCount = 3;
            break;
        default:
            userInterface.findInLine(" (.+)");
            expectedArgumentCount = 1;
            break;
        }

        try {
            MatchResult argumentMatches = userInterface.match();
            String[] arguments = new String[expectedArgumentCount];

            for (int i = 1; i <= expectedArgumentCount; i++) {
                // Extract provided arguments into array
                arguments[i - 1] = argumentMatches.group(i).strip();
            }

            return arguments;
        } catch (IllegalStateException exception) {
            throw new InvalidArgumentException(userCommand);
        }
    }
}
