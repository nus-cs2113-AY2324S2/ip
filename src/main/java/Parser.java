import InvalidInputExceptions.InvalidDeadlineException;
import InvalidInputExceptions.InvalidEventException;
import InvalidInputExceptions.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Helper class used to parse input supplied by user into CheeseBot.
 */
public class Parser {
    public boolean isBye(String command) {
        return command.equals("bye");
    }

    /**
     * Parses the input supplied by the user into CheeseBot and returns all the components in the input.
     * Assumes that the input string passed into the function is of the correct form.
     *
     * @param input Input supplied by the user of CheeseBot.
     * @return An array of fixed size of 4, consisting of the parsed components.
     */
    public String[] parseInput(String input) {
        String[] parsed = new String[4];
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            parsed[0] = input;
            return parsed;
        }

        String command = input.substring(0, spaceIndex);
        String taskName;
        String keyword;
        parsed[0] = command;

        switch (command) {
        case "find":
            keyword = input.substring(spaceIndex + 1);
            parsed[1] = keyword;
            break;

        case "todo":
            taskName = input.substring(spaceIndex + 1);
            parsed[1] = taskName;
            break;

        case "deadline":
            int byIndex = input.indexOf("/by ");

            taskName = input.substring(spaceIndex + 1, byIndex - 1);
            String by = input.substring(byIndex + 4);
            parsed[1] = taskName;
            parsed[2] = by;
            break;

        case "event":
            int fromIndex = input.indexOf(" /from");
            int toIndex = input.indexOf(" /to");

            taskName = input.substring(spaceIndex + 1, fromIndex);
            String from = input.substring(fromIndex + 7, toIndex);
            String to = input.substring(toIndex + 5);
            parsed[1] = taskName;
            parsed[2] = from;
            parsed[3] = to;
            break;

        case "mark":
            //Fallthrough
        case "unmark":
            //Fallthrough
        case "delete":
            String taskNumber = input.substring(spaceIndex + 1);
            parsed[1] = taskNumber;
            break;
        }
        return parsed;
    }
    private static void validateSingleWordCommand(String input) throws InvalidInputException {
        // for case of single word commands with no space
        if (input.equals("list") || input.equals("bye") || input.equals("help")) {
            return;
        }
        // for commands where >1 arguments are needed
        throw new InvalidInputException("\tWrong command usage or no such command!");
    }

    private static void validateEventInput(String input) throws InvalidEventException {
        int spaceIndex = input.indexOf(" ");
        int fromIndex = input.indexOf(" /from");
        int toIndex = input.indexOf(" /to");

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
            start = LocalDateTime.parse(from, CheeseBot.INPUT_FORMAT);
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
            end = LocalDateTime.parse(to, CheeseBot.INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException("\tWrong time and date format for END_TIME!");
        }

        if (start.isAfter(end)) {
            throw new InvalidEventException("\tSTART_TIME cannot be after END_TIME!");
        }
    }

    private static void validateIntegerInput(String input, int spaceIndex) throws InvalidInputException{
        int taskNumber = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (taskNumber >= CheeseBot.tasksList.getNumberOfTasks()) {
            throw new InvalidInputException("\tInvalid number! Number must be less than the number of tasks ("
                    + CheeseBot.tasksList.getNumberOfTasks() + ").");
        }

        if (taskNumber < 0) {
            throw new InvalidInputException("\tInvalid number! Task number must be more than 0.");
        }
    }
    private static void validateDeadlineInput(String input) throws InvalidInputException {
        int byIndex = input.indexOf(" /by");
        int spaceIndex = input.indexOf(" ");

        if (byIndex == -1 ) {
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
            LocalDateTime parsed = LocalDateTime.parse(deadline, CheeseBot.INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException("\tWrong time and date format for DEADLINE!");
        }
    }

    /**
     * Validates if the input string has a valid command. Then validates if input has the correct form according to the
     * type of command.
     * If input has valid command with a correct corresponding form, function returns normally.
     * Else, throws InvalidInputException.
     *
     * @param input Input supplied to CheeseBot
     * @throws InvalidInputException Throws InvalidInputException if there is not an exact number of expected arguments for
     * that particular command.
     */
    public void validateInput(String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException("\tInput is empty! Please type something.");
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            validateSingleWordCommand(input);
            return;
        }

        String command = input.substring(0, spaceIndex);
        switch (command) {
        case "todo":
            //Fallthrough
        case "find":
            return;

        case "deadline":
            validateDeadlineInput(input);
            return;

        case "event":
            validateEventInput(input);
            return;

        case "mark":
            //Fallthrough
        case "unmark":
            //Fallthrough
        case "delete":
            validateIntegerInput(input, spaceIndex);
            return;

        default: // case where there is a space but command does not match any
            throw new InvalidInputException("\tNo such command!");
        }
    }
}
