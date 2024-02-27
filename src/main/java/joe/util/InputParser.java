package joe.util;

import joe.JoeException;
import joe.command.Command;
import joe.command.ByeCommand;
import joe.command.DeleteCommand;
import joe.command.InvalidCommand;
import joe.command.NewTaskCommand;
import joe.command.ListCommand;
import joe.command.ToggleMarkCommand;
import joe.command.FindCommand;
import joe.task.TaskType;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    protected static final String FIND_COMMAND = "find";
    protected static final int INVALID_TASK_NUMBER = -69;
    protected static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Returns an executable command according to the input command word
     *
     * @param input Input string of user
     * @return a Command to execute
     */
    public static Command getCommand(String input) {
        Command command;
        String commandName = getCommandName(input);
        String arguments = getArguments(input);

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
        case FIND_COMMAND:
            command = new FindCommand(arguments);
            break;
        default:
            command = new InvalidCommand();
            break;
        }

        return command;
    }

    /**
     * Returns the command name in the user input,
     * which is separated by first whitespace in the user input.
     *
     * @param input User input
     * @return a String of the command name
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
     * @return a String containing the arguments of the input
     */
    public static String getArguments(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.substring(input.indexOf(" ")).trim();
    }

    /**
     * Returns the name of a task from a string containing the user arguments from the user input
     *
     * @param message Arguments from the user input
     * @return a String of a task name
     * @throws JoeException if the input argument does not contain a valid flag
     */
    public static String getTaskName(String message) throws JoeException {
        if (!message.contains(FLAG_INDICATOR)) {
            throw new JoeException();
        }
        return message.substring(0, message.indexOf(FLAG_INDICATOR)).trim();
    }

    /**
     * Gets the deadline time from the user input argument
     *
     * @param message User input
     * @return a LocalDateTime containing the task deadline
     * @throws JoeException if the input argument does not contain the deadline flag
     */
    public static LocalDateTime getDeadlineTime(String message) throws JoeException{
        if (!message.contains(DEADLINE_FLAG)) {
            throw new JoeException();
        }
        String time =  message.substring(message.indexOf(DEADLINE_FLAG)).replace(DEADLINE_FLAG, "").trim();

        LocalDateTime deadlineTime;
        try {
            deadlineTime = LocalDateTime.parse(time, INPUT_TIME_FORMAT);
        } catch (DateTimeException e) {
            throw new JoeException();
        }

        return deadlineTime;
    }

    /**
     * Gets the event start and end times from the user input argument
     *
     * @param message User input
     * @return a LocalDateTime array of fixed size 2 containing the event start time and end time
     * at index 0 and 1 respectively
     * @throws JoeException if the input argument does not contain the event flags
     */
    public static LocalDateTime[] getEventTime(String message) throws JoeException {
        if (!message.contains(EVENT_START_FLAG) || !message.contains(EVENT_END_FLAG)) {
            throw new JoeException();
        }

        int startIndex = message.indexOf(EVENT_START_FLAG);
        int endIndex = message.indexOf(EVENT_END_FLAG);
        String startDate = message.substring(startIndex, endIndex).replace(EVENT_START_FLAG, "").trim();
        String endDate = message.substring(endIndex).replace(EVENT_END_FLAG, "").trim();

        LocalDateTime[] eventDurations = new LocalDateTime[2];
        try {
            eventDurations[0] = LocalDateTime.parse(startDate, INPUT_TIME_FORMAT);
            eventDurations[1] = LocalDateTime.parse(endDate, INPUT_TIME_FORMAT);
        } catch (DateTimeException e) {
            throw new JoeException();
        }
        return eventDurations;
    }
}
