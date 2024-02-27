package misty;

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

    public void parseCommand(String userInput, List taskList) throws EmptyParameterException,
            IllegalListIndexException, UnknownCommandException, InvalidParameterFormatException {
        Matcher matcher = COMMAND_FORMAT.matcher(userInput);
        if(!matcher.matches()) {
            throw new UnknownCommandException();
        }

        String commandWord = matcher.group("command");
        String arguments = matcher.group("arguments");
        int index;

        switch(commandWord.trim()) {
        case "list":
            taskList.listAll();
            break;

        case "bye":
            userUi.printByeMessage();
            userUi.printMessageBorder();
            System.exit(0);

        case "todo":
            taskList.addTodo(arguments.trim());
            break;

        case "deadline":
            matcher = DEADLINE_FORMAT.matcher(arguments);
            if (!matcher.matches()) {
                userUi.printUsageDeadline();
                throw new InvalidParameterFormatException();
            }

            taskList.addDeadline(matcher.group("taskName").trim(), matcher.group("by").trim());
            break;

        case "event":
            matcher = EVENT_FORMAT.matcher(arguments);
            if (!matcher.matches()) {
                userUi.printUsageEvent();
                throw new InvalidParameterFormatException();
            }

            taskList.addEvent(matcher.group("taskName").trim(), matcher.group("from").trim(),
                    matcher.group("to").trim());
            break;

        case "mark":
            matcher = INDEX_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageMark();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            taskList.markTask(index);
            break;

        case "unmark":
            matcher = INDEX_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageUnmark();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            taskList.unmarkTask(index);
            break;

        case "delete":
            matcher = INDEX_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                userUi.printUsageDelete();
                throw new IllegalListIndexException();
            }

            index = Integer.parseInt(matcher.group("index"));
            taskList.deleteTask(index);
            break;

        default:
            throw new UnknownCommandException();
        }
    }
}