package misty.parser;

import java.util.regex.Matcher;
import misty.command.ByeCommand;
import misty.command.CheckCommand;
import misty.command.Command;
import misty.command.DeadlineCommand;
import misty.command.DeleteCommand;
import misty.command.EventCommand;
import misty.command.FindCommand;
import misty.command.ListCommand;
import misty.command.MarkCommand;
import misty.command.TodoCommand;
import misty.command.UnmarkCommand;
import misty.data.TaskList;
import misty.data.exception.IllegalListIndexException;
import misty.data.exception.InvalidParameterFormatException;
import misty.data.exception.UnknownCommandException;
import misty.ui.UserUi;

/**
 * Parses commands provided by user.
 */
public class Parser {
    private UserUi userUi;

    /**
     * Constructs Parser object.
     *
     * @param userUi UserUi object used to interact with user.
     */
    public Parser(UserUi userUi) {
        this.userUi = userUi;
    }

    /**
     * Parse command given by user and decides what to execute next.
     *
     * @param userInput String input provided by the user to chatbot.
     * @param taskList TaskList object used to store all tasks.
     * @return Command object corresponding to the appropriate command provided by user.
     * @throws IllegalListIndexException If index <= 0 or index > size of task list.
     * @throws UnknownCommandException If command provided by user is unknown.
     * @throws InvalidParameterFormatException If parameters given by user is not in expected format.
     */
    public Command parseCommand(String userInput, TaskList taskList) throws IllegalListIndexException,
            UnknownCommandException, InvalidParameterFormatException {
        Matcher matcher = Command.COMMAND_FORMAT.matcher(userInput);
        if(!matcher.matches()) {
            throw new UnknownCommandException();
        }

        String commandWord = matcher.group("command");
        String arguments = matcher.group("arguments");
        int index;

        switch(commandWord.trim()) {
        case ListCommand.COMMAND_STRING:
            return new ListCommand();

        case ByeCommand.COMMAND_STRING:
            return new ByeCommand();

        case TodoCommand.COMMAND_STRING:
            return new TodoCommand((arguments.trim()));

        case DeadlineCommand.COMMAND_STRING:
            matcher = DeadlineCommand.COMMAND_FORMAT.matcher(arguments);
            if (!matcher.matches()) {
                userUi.printUsageDeadline();
                throw new InvalidParameterFormatException();
            }

            return new DeadlineCommand(matcher.group("taskName").trim(), matcher.group("by").trim());

        case EventCommand.COMMAND_STRING:
            matcher = EventCommand.COMMAND_FORMAT.matcher(arguments);
            if (!matcher.matches()) {
                userUi.printUsageEvent();
                throw new InvalidParameterFormatException();
            }

            return new EventCommand(matcher.group("taskName").trim(), matcher.group("from").trim(),
                    matcher.group("to").trim());

        case MarkCommand.COMMAND_STRING:
            matcher = MarkCommand.COMMAND_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageMark();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            return new MarkCommand(index);

        case UnmarkCommand.COMMAND_STRING:
            matcher = UnmarkCommand.COMMAND_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageUnmark();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            return new UnmarkCommand(index);

        case DeleteCommand.COMMAND_STRING:
            matcher = DeleteCommand.COMMAND_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageDelete();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            return new DeleteCommand(index);

        case CheckCommand.COMMAND_STRING:
            return new CheckCommand(arguments.trim());

        case FindCommand.COMMAND_STRING:
            return new FindCommand(arguments.trim());

        default:
            throw new UnknownCommandException();
        }
    }
}