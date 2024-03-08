package hachi.data;

import hachi.data.task.Task;

import java.util.ArrayList;

/**
 * Represent the error handling class for the chatbot Hachi.
 *
 * @author clarencepohh
 * @version 01/03/2024
 *
 */

public class HachiException extends Exception {
    public static final String INVALID_INPUT_MESSAGE = "Not marvelous! I'm not sure what you're saying here...";
    public static final String EMPTY_TASK_LIST_MESSAGE = "It appears you have no tasks at the moment...";
    public static final String EMPTY_TASK_DESCRIPTION_MESSAGE = "Where's the task description? " +
            "Type 'help' without quotes if you need to see the format! ";
    public static final String MISSING_DEADLINE_BY_DATE_MESSAGE = "Looks like you're missing the complete-by date! " +
            "Remember: /by <date here>!";
    public static final String MISSING_EVENT_START_END_DATE_MESSAGE = "Your start or end dates have some formatting " +
            "issues. It's: /from <start date> /to <end date>";
    public static final String LIST_OUT_OF_BOUNDS_MESSAGE = "Woah, I'm not so sure if that task exists. You might " +
            "want to check that task number again.";
    public static final String CORRUPTED_SAVE_MESSAGE = "The data stored looks corrupted. Getting rid of it.";
    public static final String MISSING_FIND_DESCRIPTION_MESSAGE = "What exactly do you want to find? Please state it" +
            " after '/find'.";
    public static final String EMPTY_FOUND_TASK_LIST = "Looks like there aren't any tasks like that...";

    public HachiException (String errorMessage) {
        super(errorMessage);
    }

    /**
     * Prints to the console when an unrecognised input is given by the user.
     *
     * @throws HachiException If the input is not recognised.
     */

    public static void invalidInput() throws HachiException {
        throw new HachiException(INVALID_INPUT_MESSAGE);
    }

    /**
     * Checks if the task list is empty and prints an error message if command
     * given requires a populated task list to be executed.
     *
     * @param numTasks The number of tasks currently in the task list.
     * @throws HachiException If numTasks is 0.
     */

    public static void checkEmptyList(int numTasks) throws HachiException {
        if (numTasks == 0) {
            throw new HachiException(EMPTY_TASK_LIST_MESSAGE);
        }
    }

    /**
     * Checks if the user input for task-creation related commands (todo, event, deadline)
     * contains a task description, and prints an error message to the console if it is missing.
     *
     * @param line The String containing the whole user input.
     * @throws HachiException if there is no task description.
     */

    public static void checkValidDescription (String line) throws HachiException {
        String[] userWords = line.split(" ");
        if (userWords.length == 1) {
            throw new HachiException(EMPTY_TASK_DESCRIPTION_MESSAGE);
        }
    }

    /**
     * Checks if deadlines to be created have a by date, and prints an error message to
     * the console if it is missing. Also checks if deadlines have the required "/by" command.
     *
     * @param indexOfBy The int representing the index of the character after "/by" in the input string.
     * @param line The String containing the whole user input.
     * @throws HachiException If the required input format is incorrect.
     */

    public static void checkDeadlineByDate (int indexOfBy, String line) throws HachiException {
        String[] afterBySubstring = line.split("/by");
        if (afterBySubstring.length == 1 || indexOfBy == 2 || afterBySubstring[1].isBlank()) {
            throw new HachiException(MISSING_DEADLINE_BY_DATE_MESSAGE);
        }
    }

    /**
     * Checks if events to be created have a from and to date, and prints an error message to
     * the console if they are missing. Also checks if events have the required "/from" and "/to" commands.
     *
     * @param indexOfStart The int representing the index of the character after "/from" in the input string.
     * @param indexOfEnd The int representing the index of the character after "/to" in the input string.
     * @param line The string containing the whole user input.
     * @throws HachiException If the required input format is incorrect.
     */

    public static void checkEventDates (int indexOfStart, int indexOfEnd, String line) throws HachiException {
        boolean isMissingDate = false;
        boolean isDateEmpty = false;

        String substringWithFrom = line.substring(indexOfStart - 5, indexOfEnd - 4);
        String substringWithTo = line.substring(indexOfEnd - 4);

        String[] afterFromSubstring = substringWithFrom.split("/from");
        String[] afterToSubstring = substringWithTo.split("/to");

        if (afterFromSubstring.length == 1 || afterToSubstring.length == 1) {
            isMissingDate = true;
        } else try {
            if (afterFromSubstring[1].isBlank() || afterToSubstring[1].isBlank()) {
                isDateEmpty = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new HachiException(MISSING_EVENT_START_END_DATE_MESSAGE);
        }

        if (indexOfEnd == 2 || indexOfStart == 4 || isMissingDate || isDateEmpty) {
            throw new HachiException(MISSING_EVENT_START_END_DATE_MESSAGE);
        }
    }

    /**
     * Checks if the task to be accessed exists within the current task list, and prints an
     * error message to the console if it is out of bounds.
     *
     * @param taskNumber The int representing the task number of the task to be accessed.
     * @throws HachiException If taskNumber is out of bounds of the task list.
     */

    public static void checkOutOfBounds (int taskNumber) throws HachiException{
        int currentLastTask = Task.getTotalNumTasks();

        if (taskNumber > currentLastTask || taskNumber <= 0) {
            throw new HachiException(LIST_OUT_OF_BOUNDS_MESSAGE);
        }
    }

    /**
     * Function that checks if the user input after the 'find' command is valid, and prints an
     * error message to the console if it is invalid.
     *
     * @param indexOfFind The int representing the index of the character after 'find' in the input string.
     * @param cleanedInput The String containing the cleaned whole user input.
     * @throws HachiException If the user input is invalid.
     */

    public static void checkFindTaskDescription (int indexOfFind, String cleanedInput) throws HachiException {
        boolean isMissingDescription = false;

        String[] afterFromSubstring = cleanedInput.split("FIND");

        if (afterFromSubstring.length == 1) {
            isMissingDescription = true;
        } else try {
            if (afterFromSubstring[1].isBlank()) {
                throw new HachiException(MISSING_FIND_DESCRIPTION_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            isMissingDescription = true;
        }

        if (isMissingDescription || indexOfFind == 4) {
            throw new HachiException(MISSING_FIND_DESCRIPTION_MESSAGE);
        }
    }

    /**
     * Function that checks if the returned task list that contains tasks matching the user's
     * requested keyword is empty, and prints an error message to the console if it is empty.
     *
     * @param foundTasksList The ArrayList<Task> containing the tasks found to be matching.
     * @throws HachiException If foundTasksList is empty.
     */

    public static void checkForEmptyFoundTaskList (ArrayList<Task> foundTasksList) throws HachiException{
        if (foundTasksList.isEmpty()) {
            throw new HachiException(EMPTY_FOUND_TASK_LIST);
        }
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
