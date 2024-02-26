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

    public static String getCommandName(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    public static String getArguments(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.substring(input.indexOf(" ")).trim();
    }

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
