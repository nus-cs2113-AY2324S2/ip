import InvalidInputExceptions.InvalidDeadlineException;
import InvalidInputExceptions.InvalidEventException;
import InvalidInputExceptions.InvalidInputException;
import Tasks.TasksList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper class used to validate and parse input supplied by user into CheeseBot.
 */
public class Parser {
    private static final TasksList TASKS_LIST = CheeseBot.TASKS_LIST;
    private static final DateTimeFormatter INPUT_FORMAT = CheeseBot.INPUT_FORMAT;
    private static final String SPACE = " ";
    private static final String BY = " /by";
    private static final String FROM = " /from";
    private static final String TO = " /to";


    /**
     * Returns True if command is "bye".
     *
     * @param command Input entered by user
     * @return True if command is "bye", else False.
     */
    public boolean isBye(String command) {
        return command.equals("bye");
    }

    private static String[] parseSingleWordCommand(String input) throws InvalidInputException {
        // for case of single word commands with no space
        if (input.equals("list") || input.equals("bye") || input.equals("help")) {
            String[] parsed = new String[4];
            parsed[0] = input;
            return parsed;
        }
        // for commands where >1 arguments are needed
        throw new InvalidInputException("\tWrong command usage or no such command!");
    }

    private static String[] parseDoubleWordCommand(String input) {
        int spaceIndex = input.indexOf(SPACE);
        String command = input.substring(0, spaceIndex);
        String argument = input.substring(spaceIndex + 1);
        String[] parsed = new String[4];
        parsed[0] = command;
        parsed[1] = argument;
        return parsed;
    }

    private static String[] validateAndParseDeadlineInput(String input) throws InvalidInputException {
        int byIndex = input.indexOf(BY);
        int spaceIndex = input.indexOf(SPACE);

        if (byIndex == -1) {
            throw new InvalidDeadlineException("\tMissing '/by' flag!");
        }

        if (spaceIndex == byIndex || spaceIndex + 1 == byIndex) {
            //case where there is no task name input
            throw new InvalidDeadlineException("\tMissing TASK_NAME!");
        }

        String taskName = input.substring(spaceIndex + 1, byIndex).strip();
        if (taskName.isEmpty()) {
            throw new InvalidDeadlineException("\tMissing TASK_NAME!");
        }

        if (input.charAt(byIndex + 4) != ' ') {
            throw new InvalidDeadlineException("\tInvalid format! Please provide a space between /by and DEADLINE");
        }

        if (byIndex + 5 > input.length()) {
            //case where input ends immediately after /by flag
            throw new InvalidDeadlineException("\tMissing DEADLINE!");
        }

        String deadline = input.substring(byIndex + 5);
        try {
            LocalDateTime by = LocalDateTime.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException("\tWrong time and date format for DEADLINE!");
        }

        String[] parsed = new String[4];
        parsed[0] = "deadline";
        parsed[1] = taskName;
        parsed[2] = deadline;
        return parsed;
    }

    private static String[] validateAndParseEventInput(String input) throws InvalidEventException {
        int spaceIndex = input.indexOf(SPACE);
        int fromIndex = input.indexOf(FROM);
        int toIndex = input.indexOf(TO);

        if (fromIndex == spaceIndex || fromIndex == spaceIndex + 1) {
            //case where there is no task name input
            throw new InvalidEventException("\tMissing TASK_NAME!");
        }

        if (fromIndex == -1) {
            throw new InvalidEventException("\tMissing '/from' flag!");
        }

        if (toIndex == -1) {
            throw new InvalidEventException("\tMissing '/to' flag!");
        }

        if (fromIndex > toIndex) {
            //case where /to is placed in front of /from
            throw new InvalidEventException("\tWrong order of flags!");
        }

        String taskName = input.substring(spaceIndex + 1, fromIndex).strip();
        if (taskName.isEmpty()) {
            throw new InvalidEventException("\tMissing TASK_NAME!");
        }

        if (fromIndex + 7 > input.length()) {
            //case where input ends immediately after /from
            throw new InvalidEventException("\tMissing START_TIME!");
        }

        if (fromIndex + 7 >= toIndex) {
            //case where argument for /from is missing
            throw new InvalidEventException("\tMissing START_TIME!");
        }

        if (input.charAt(fromIndex + 6) != ' ') {
            throw new InvalidEventException("\tInvalid format! Please provide a space between /from and START_TIME");
        }

        String from = input.substring(fromIndex + 7, toIndex);
        LocalDateTime start;
        try {
            start = LocalDateTime.parse(from, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException("\tWrong time and date format for START_TIME!");
        }

        if (toIndex + 4 >= input.length()) {
            //case where input ends immediately after /to
            throw new InvalidEventException("\tMissing END_TIME!");
        }

        if (input.charAt(toIndex + 4) != ' ') {
            throw new InvalidEventException("\tInvalid format! Please provide a space between /to and END_TIME");
        }

        String to = input.substring(toIndex + 5);
        LocalDateTime end;
        try {
            end = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException("\tWrong time and date format for END_TIME!");
        }

        if (start.isAfter(end)) {
            throw new InvalidEventException("\tSTART_TIME cannot be after END_TIME!");
        } else if (start.isEqual(end)) {
            throw new InvalidEventException("\tEND_TIME cannot end on the same time as START_TIME");
        }

        String[] parsed = new String[4];
        parsed[0] = "event";
        parsed[1] = taskName;
        parsed[2] = from;
        parsed[3] = to;
        return parsed;
    }

    private static String[] validateAndParseIntegerInput(String input) throws InvalidInputException {
        if (TASKS_LIST.getNumberOfTasks() == 0) {
            throw new InvalidInputException("\tList is empty! Nothing can be deleted.");
        }
        int spaceIndex = input.indexOf(SPACE);
        int taskNumber = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (taskNumber >= TASKS_LIST.getNumberOfTasks()) {
            throw new InvalidInputException("\tInvalid number! Number cannot be more than the total number of tasks ("
                    + TASKS_LIST.getNumberOfTasks() + ").");
        }

        if (taskNumber < 0) {
            throw new InvalidInputException("\tInvalid number! Task number must be more than 0.");
        }
        String[] parsed = new String[4];
        parsed[0] = input.substring(0, spaceIndex);
        parsed[1] = String.valueOf(taskNumber);
        return parsed;
    }

    /**
     * Validates if an input is of the correct format, then parse it accordingly to the command.
     * First, checks if input is a single word command. Next, checks if input is a double word command. Then, checks if
     * command takes in an integer input. Lastly, check if command matches any of the task-adding commands. Else, reject
     * command input and throw InvalidInputException.
     *
     * @param input Input supplied to CheeseBot
     * @throws InvalidInputException Throws InvalidInputException if the arguments supplied does not match the
     *                               command requirements.
     */
    public String[] validateAndParseInput(String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException("\tInput is empty! Please type something.");
        }

        String[] parsed;
        if (!input.contains(SPACE)) {
            parsed = parseSingleWordCommand(input);
            return parsed;
        }

        String command = input.substring(0, input.indexOf(SPACE));
        switch (command) {
        case "todo":
            //Fallthrough
        case "find":
            parsed = parseDoubleWordCommand(input);
            break;

        case "mark":
            //Fallthrough
        case "unmark":
            //Fallthrough
        case "delete":
            parsed = validateAndParseIntegerInput(input);
            break;

        case "deadline":
            parsed = validateAndParseDeadlineInput(input);
            break;

        case "event":
            parsed = validateAndParseEventInput(input);
            break;

        default: // case where there is a space but command does not match any
            throw new InvalidInputException("\tNo such command!");
        }
        return parsed;
    }
}