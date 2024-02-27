package misty.parser;

import misty.data.TaskList;
import misty.command.*;
import misty.data.exception.*;
import misty.ui.UserUi;
import java.util.regex.Matcher;

public class Parser {
    private UserUi userUi;

    public Parser(UserUi userUi) {
        this.userUi = userUi;
    }

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

        default:
            throw new UnknownCommandException();
        }
    }
}