package joe.util;

import joe.JoeException;
import joe.command.Command;
import joe.command.ByeCommand;
import joe.command.DeleteCommand;
import joe.command.InvalidCommand;
import joe.command.NewTaskCommand;
import joe.command.ListCommand;
import joe.command.ToggleMarkCommand;
import joe.task.TaskType;

public class InputParser {
    protected static final String FLAG_INDICATOR = "/";
    protected static final String DEADLINE_FLAG =  FLAG_INDICATOR + "by";
    protected static final String EVENT_START_FLAG = FLAG_INDICATOR + "from";
    protected static final String EVENT_END_FLAG = FLAG_INDICATOR + "to";
    protected static final String EXIT_COMMAND = "bye";
    protected static final String LIST_COMMAND = "list";
    protected static final String MARK_COMMAND = "mark";
    protected static final String UNMARK_COMMAND = "unmark";
    protected static final String NEW_TODO_COMMAND = "todo";
    protected static final String NEW_DEADLINE_COMMAND = "deadline";
    protected static final String NEW_EVENT_COMMAND = "event";
    protected static final String DELETE_COMMAND = "delete";
    protected static final int INVALID_TASK_NUMBER = -69;

    /**
     * Returns an executable command according to the input command word
     *
     * @param input Input string of user
     * @return Command to execute
     */
    public static Command getCommand(String input) {
        Command command;
        String commandName = getCommandName(input);
        String arguments = getMessage(input);

        int taskNumber = INVALID_TASK_NUMBER;
        try {
            taskNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException ignored) {
            // Error is handled in commands that use task numbers
        }

        switch (commandName) {
        case EXIT_COMMAND:
            command = new ByeCommand(arguments);
            break;
        case LIST_COMMAND:
            command = new ListCommand(arguments);
            break;
        case NEW_TODO_COMMAND:
            command = new NewTaskCommand(arguments, TaskType.TODO);
            break;
        case NEW_DEADLINE_COMMAND:
            command = new NewTaskCommand(arguments, TaskType.DEADLINE);
            break;
        case NEW_EVENT_COMMAND:
            command = new NewTaskCommand(arguments, TaskType.EVENT);
            break;
        case MARK_COMMAND:
        case UNMARK_COMMAND:
            command = new ToggleMarkCommand(taskNumber, commandName.equals(MARK_COMMAND));
            break;
        case DELETE_COMMAND:
            command = new DeleteCommand(taskNumber);
            break;
        default:
            command = new InvalidCommand();
            break;
        }

        return command;
    }

    /**
     * Returns the command word in the user input,
     * which is separated by first whitespace in the user input.
     *
     * @param input User input
     * @return Name of Command
     */
    public static String getCommandName(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    /**
     * Returns a string containing the arguments in the user input,
     * which is the string of characters after the first whitespace
     *
     * @param input User input
     * @return A string containing the arguments of the input
     */
    public static String getMessage(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.substring(input.indexOf(" ")).trim();
    }

    /**
     * Returns the name of a task from a string containing the user arguments from the user input
     *
     * @param message Arguments from the user input
     * @return A task name
     * @throws JoeException if the input argument does not contain a valid flag
     */
    public static String getTaskName(String message) throws JoeException {
        if (!message.contains(FLAG_INDICATOR)) {
            throw new JoeException();
        }
        return message.substring(0, message.indexOf(FLAG_INDICATOR)).trim();
    }

    public static String getDeadlineTime(String message) throws JoeException{
        if (!message.contains(DEADLINE_FLAG)) {
            throw new JoeException();
        }
        return message.substring(message.indexOf(DEADLINE_FLAG)).replace(DEADLINE_FLAG, "").trim();
    }

    public static String[] getEventTime(String message) throws JoeException {
        if (!message.contains(EVENT_START_FLAG) || !message.contains(EVENT_END_FLAG)) {
            throw new JoeException();
        }
        String[] eventDurations = new String[2];
        int startIndex = message.indexOf(EVENT_START_FLAG);
        int endIndex = message.indexOf(EVENT_END_FLAG);
        eventDurations[0] = message.substring(startIndex, endIndex).replace(EVENT_START_FLAG, "").trim();
        eventDurations[1] = message.substring(endIndex).replace(EVENT_END_FLAG, "").trim();
        return eventDurations;
    }
}
