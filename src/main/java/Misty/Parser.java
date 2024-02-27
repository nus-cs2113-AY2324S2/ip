package misty;

import misty.command.*;
import misty.exception.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private UserUi userUi;

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");
    private static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<taskName>.*)\\s/by\\s(?<by>.*)");
    private static final Pattern EVENT_FORMAT =
            Pattern.compile("(?<taskName>.*)\\s/from\\s(?<from>.*)\\s/to\\s(?<to>.*)");
    private static final Pattern INDEX_FORMAT = Pattern.compile("(?<index>\\d+)");

    public Parser(UserUi userUi) {
        this.userUi = userUi;
    }

    public Command parseCommand(String userInput, List taskList) throws IllegalListIndexException,
            UnknownCommandException, InvalidParameterFormatException {
        Matcher matcher = COMMAND_FORMAT.matcher(userInput);
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
            matcher = DEADLINE_FORMAT.matcher(arguments);
            if (!matcher.matches()) {
                userUi.printUsageDeadline();
                throw new InvalidParameterFormatException();
            }

            return new DeadlineCommand(matcher.group("taskName").trim(), matcher.group("by").trim());

        case EventCommand.COMMAND_STRING:
            matcher = EVENT_FORMAT.matcher(arguments);
            if (!matcher.matches()) {
                userUi.printUsageEvent();
                throw new InvalidParameterFormatException();
            }

            return new EventCommand(matcher.group("taskName").trim(), matcher.group("from").trim(),
                    matcher.group("to").trim());

        case MarkCommand.COMMAND_STRING:
            matcher = INDEX_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageMark();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            return new MarkCommand(index);

        case UnmarkCommand.COMMAND_STRING:
            matcher = INDEX_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageUnmark();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            return new UnmarkCommand(index);

        case DeleteCommand.COMMAND_STRING:
            matcher = INDEX_FORMAT.matcher(arguments.trim());
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