package carrot.parser;

import carrot.task.Deadline;
import carrot.task.Event;
import carrot.task.Task;
import carrot.task.Todo;

import carrot.command.CommandType;
import carrot.command.Command;
import carrot.command.HelpCommand;
import carrot.command.TodoCommand;
import carrot.command.DeadlineCommand;
import carrot.command.FindCommand;
import carrot.command.EventCommand;
import carrot.command.MarkCommand;
import carrot.command.UnmarkCommand;
import carrot.command.ListCommand;
import carrot.command.DeleteCommand;
import carrot.command.ExitCommand;
import carrot.command.InvalidCommand;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses user input from input scanner and text file storage,
 * and provides methods to extract command types and command arguments
 */
public class Parser {

    // Regular expressions for parsing user commands from Scanner input
    private static final Pattern MARK_PATTERN =
            Pattern.compile("mark (?<taskIndex>\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern UNMARK_PATTERN =
            Pattern.compile("unmark (?<taskIndex>\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_PATTERN =
            Pattern.compile("delete (?<taskIndex>\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern LIST_PATTERN =
            Pattern.compile("list", Pattern.CASE_INSENSITIVE);
    private static final Pattern TODO_PATTERN =
            Pattern.compile("todo (?<taskDescription>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern FIND_PATTERN =
            Pattern.compile("find (?<taskDescription>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("deadline (?<taskDescription>.+) /(?<by>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("event (?<taskDescription>.+) /(?<from>.+) /(?<to>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern HELP_PATTERN =
            Pattern.compile("help", Pattern.CASE_INSENSITIVE);
    private static final Pattern EXIT_PATTERN =
            Pattern.compile("bye", Pattern.CASE_INSENSITIVE);

    // Regular expressions for parsing task data from text file storage lines
    private static final Pattern TODO_DATA_PATTERN =
            Pattern.compile("T \\| (?<isDone>true|false) \\| \"(?<description>.+?)\"");
    private static final Pattern DEADLINE_DATA_PATTERN =
            Pattern.compile("D \\| (?<isDone>true|false) \\| \"(?<description>.+?)\" \\| \"(?<by>.+?)\"");
    private static final Pattern EVENT_DATA_PATTERN =
            Pattern.compile("E \\| (?<isDone>true|false) \\| \"(?<description>.+?)\" \\| " +
                    "\"(?<from>.+?)\" \\| \"(?<to>.+?)\"");

    // Maps command types to corresponding regular expressions and command objects
    private static Map<CommandType, Pattern> commandPatternMap = initCommandPatternMap();
    private static Map<CommandType, Command> commandClassMap = initCommandClassMap();
    private static Map<CommandType, String[]> commandGroupMap = initCommandGroupMap();

    private static Map<CommandType, Pattern> initCommandPatternMap() {
        Map<CommandType, Pattern> commandPatternMap = new HashMap<>();

        commandPatternMap.put(CommandType.EXIT, EXIT_PATTERN);
        commandPatternMap.put(CommandType.HELP, HELP_PATTERN);
        commandPatternMap.put(CommandType.MARK, MARK_PATTERN);
        commandPatternMap.put(CommandType.UNMARK, UNMARK_PATTERN);
        commandPatternMap.put(CommandType.DELETE, DELETE_PATTERN);
        commandPatternMap.put(CommandType.LIST, LIST_PATTERN);
        commandPatternMap.put(CommandType.TODO, TODO_PATTERN);
        commandPatternMap.put(CommandType.FIND, FIND_PATTERN);
        commandPatternMap.put(CommandType.DEADLINE, DEADLINE_PATTERN);
        commandPatternMap.put(CommandType.EVENT, EVENT_PATTERN);

        return commandPatternMap;
    }

    private static Map<CommandType, Command> initCommandClassMap() {
        Map<CommandType, Command> commandClassMap = new HashMap<>();

        commandClassMap.put(CommandType.HELP, new HelpCommand());
        commandClassMap.put(CommandType.LIST, new ListCommand());
        commandClassMap.put(CommandType.TODO, new TodoCommand());
        commandClassMap.put(CommandType.DEADLINE, new DeadlineCommand());
        commandClassMap.put(CommandType.EVENT, new EventCommand());
        commandClassMap.put(CommandType.DELETE, new DeleteCommand());
        commandClassMap.put(CommandType.MARK, new MarkCommand());
        commandClassMap.put(CommandType.UNMARK, new UnmarkCommand());
        commandClassMap.put(CommandType.FIND, new FindCommand());
        commandClassMap.put(CommandType.EXIT, new ExitCommand());
        commandClassMap.put(CommandType.INVALID, new InvalidCommand());

        return commandClassMap;
    }

    private static Map<CommandType, String[]> initCommandGroupMap() {
        Map<CommandType, String[]> commandGroupMap = new HashMap<>();

        commandGroupMap.put(CommandType.MARK, new String[]{"taskIndex"});
        commandGroupMap.put(CommandType.UNMARK, new String[]{"taskIndex"});
        commandGroupMap.put(CommandType.DELETE, new String[]{"taskIndex"});
        commandGroupMap.put(CommandType.TODO, new String[]{"taskDescription"});
        commandGroupMap.put(CommandType.FIND, new String[]{"taskDescription"});
        commandGroupMap.put(CommandType.DEADLINE, new String[]{"taskDescription", "by"});
        commandGroupMap.put(CommandType.EVENT, new String[]{"taskDescription", "from", "to"});

        return commandGroupMap;
    }

    /**
     * Parses the user input and returns the corresponding Command class.
     *
     * @param userInput the user input string
     * @return an instance of the command class corresponding to the user input
     */
    public static Command getCommandType(String userInput) {
        for (Map.Entry<CommandType, Pattern> entry : commandPatternMap.entrySet()) {
            // Retrieve the Pattern associated with the command in the entry
            Pattern commandPattern = entry.getValue();

            // Create a Matcher object using the current Pattern and the user input
            Matcher matcher = commandPattern.matcher(userInput);

            // Check if the entire input sequence matches the pattern
            if (matcher.matches()) {
                // If there is a match, return the corresponding Command
                CommandType commandtype = entry.getKey();
                Command command = commandClassMap.getOrDefault(commandtype, new InvalidCommand());
                return command;
            }
        }
        return new InvalidCommand();
    }


    /**
     * Parses the command arguments from the user input based on the command type.
     *
     * @param command   the command type in enum format
     * @param userInput the user input string
     * @return an array of command arguments that corresponds to the command
     */
    public static String[] getCommandArguments(CommandType command, String userInput) {
        Matcher matcher = commandPatternMap.get(command).matcher(userInput);
        if (!matcher.matches()) {
            return null;
        }

        String[] groupNames = commandGroupMap.get(command);
        String[] arguments = new String[groupNames.length];

        for (int i = 0; i < groupNames.length; i++) {
            arguments[i] = matcher.group(groupNames[i]);
        }

        return arguments;
    }

    /**
     * Parses a task from the storage text file line.
     *
     * @param line the text file line representing a task
     * @return the parsed task object
     */
    public static Task parseTaskFromTextFileLine(String line) {
        Pattern[] dataPatterns = {TODO_DATA_PATTERN, DEADLINE_DATA_PATTERN, EVENT_DATA_PATTERN};

        for (int i = 0; i < dataPatterns.length; i++) {
            Pattern pattern = dataPatterns[i];
            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                boolean isDone = Boolean.parseBoolean(matcher.group("isDone"));
                String description = matcher.group("description");

                switch (i) {
                case 0: // Index of TODO_DATA_PATTERN
                    return new Todo(description, isDone);
                case 1: // Index of DEADLINE_DATA_PATTERN
                    String by = matcher.group("by");
                    return new Deadline(description, by, isDone);
                case 2: // Index of EVENT_DATA_PATTERN
                    String from = matcher.group("from");
                    String to = matcher.group("to");
                    return new Event(description, from, to, isDone);
                }
                break;
            }
        }
        return null;
    }
}
