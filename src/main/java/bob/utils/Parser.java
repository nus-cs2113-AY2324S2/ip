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

import java.util.regex.MatchResult;

/**
 * Parses User inputs and generates the appropriate Commands.
 */
public class Parser {
    /**
     * Returns the appropriate Command based off the User's input.
     *
     * @param userCommand Command String provided by the User (e.g. LIST, DEADLINE etc.).
     * @param taskManager TaskManager instance belonging to the current instance of the program.
     * @param userInterface Ui instance belonging to the current instance of the program.
     * @return Command corresponding to the user's input.
     * @throws InvalidTaskNumberException If invalid task ID is provided for MARK, UNMARK, DELETE commands.
     * @throws InvalidArgumentException If arguments are not formatted correctly in the User input.
     * @throws InvalidCommandException If invalid commands are provided by the User.
     */
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

    /**
     * Returns a Task "Utility" Command (e.g. mark, unmark, delete)
     *
     * @param taskManager TaskManager instance belonging to the current instance of the program.
     * @param userCommand Command String provided by the User. Must be a "utility" command.
     * @param taskId ID of the Task to be modified.
     * @return Command corresponding to the User's input.
     */
    private Command getTaskUtilityCommand(TaskManager taskManager, String userCommand, int taskId) {
        if (userCommand.equals("MARK")) {
            return new MarkCommand(taskManager, taskId);
        } else if (userCommand.equals("UNMARK")) {
            return new UnmarkCommand(taskManager, taskId);
        } else {
            return new DeleteCommand(taskManager, taskId);
        }
    }

    /**
     * Returns a Task "Creation" Command (e.g. todo, deadline, event)
     *
     * @param taskManager TaskManager instance belonging to the current instance of the program.
     * @param userCommand Command String provided by the User. Must be a "creation" command.
     * @param arguments String array of arguments provided by the User. Used to create the appropriate Task.
     * @return Command corresponding to the User's input.
     */
    private Command getTaskCreationCommand(TaskManager taskManager, String userCommand, String[] arguments) {
        String taskName = arguments[0];

        if (userCommand.equals("TODO")) {
            return new TodoCommand(taskManager, taskName);
        } else if (userCommand.equals("DEADLINE")) {
            String dueDate = arguments[1];
            return new DeadlineCommand(taskManager, taskName, dueDate);
        } else {
            String startDate = arguments[1];
            String endDate = arguments[2];
            return new EventCommand(taskManager, taskName, startDate, endDate);
        }
    }

    /**
     * Parse arguments provided by the User from standard input.
     *
     * @param userCommand Command String provided by the User.
     * @param userInterface Ui instance belonging to the current instance of the program.
     * @return String array of arguments provided by the User.
     * @throws InvalidArgumentException If arguments are not provided in the expected format.
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
