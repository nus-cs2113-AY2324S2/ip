import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final Pattern MARK_PATTERN =
            Pattern.compile("mark (?<taskIndex>\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern UNMARK_PATTERN =
            Pattern.compile("unmark (?<taskIndex>\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern LIST_PATTERN =
            Pattern.compile("list", Pattern.CASE_INSENSITIVE);
    private static final Pattern TODO_PATTERN =
            Pattern.compile("todo (?<taskDescription>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("deadline (?<taskDescription>.+) /(?<by>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("event (?<taskDescription>.+) /(?<from>.+) /(?<to>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern HELP_PATTERN =
            Pattern.compile("help", Pattern.CASE_INSENSITIVE);
    private static final Pattern EXIT_PATTERN =
            Pattern.compile("bye", Pattern.CASE_INSENSITIVE);

    private static Map<CommandType, Pattern> commandPatternMap = initCommandTypes();

    private static Map<CommandType, Pattern> initCommandTypes() {
        Map<CommandType, Pattern> commandPatternMap = new HashMap<>();

        commandPatternMap.put(CommandType.EXIT, EXIT_PATTERN);
        commandPatternMap.put(CommandType.HELP, HELP_PATTERN);
        commandPatternMap.put(CommandType.MARK, MARK_PATTERN);
        commandPatternMap.put(CommandType.UNMARK, UNMARK_PATTERN);
        commandPatternMap.put(CommandType.LIST, LIST_PATTERN);
        commandPatternMap.put(CommandType.TODO, TODO_PATTERN);
        commandPatternMap.put(CommandType.DEADLINE, DEADLINE_PATTERN);
        commandPatternMap.put(CommandType.EVENT, EVENT_PATTERN);

        return commandPatternMap;
    }

    protected static CommandType getCommandType(String userInput) {
        for (Map.Entry<CommandType, Pattern> entry : commandPatternMap.entrySet()) {
            // Retrieve the Pattern associated with the command in the entry
            Pattern commandPattern = entry.getValue();

            // Create a Matcher object using the current Pattern and the user input
            Matcher matcher = commandPattern.matcher(userInput);

            // Check if the entire input sequence matches the pattern
            if (matcher.matches()) {
                // If there is a match, return the corresponding CommandType
                return entry.getKey();
            }
        }
        return CommandType.INVALID;
    }

    protected static String[] getCommandArguments(CommandType command, String userInput) {
        Matcher matcher = commandPatternMap.get(command).matcher(userInput);
        if (!matcher.matches()) {
            return null;
        }

        String[] arguments = new String[3];

        switch (command) {
        case MARK:
        case UNMARK:
            arguments[0] = matcher.group("taskIndex");
            break;
        case TODO:
            arguments[0] = matcher.group("taskDescription");
            break;
        case DEADLINE:
            arguments[0] = matcher.group("taskDescription");
            arguments[1] = matcher.group("by");
            break;
        case EVENT:
            arguments[0] = matcher.group("taskDescription");
            arguments[1] = matcher.group("from");
            arguments[2] = matcher.group("to");
            break;
        default:
            return null;
        }
        return arguments;
    }
}
