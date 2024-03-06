package mona.input;

import mona.exception.MonaException;
import mona.util.Constants;
import mona.manager.TaskList;

/**
 * Validates the input commands.
 * Ensures that the commands adhere to the expected format and constraints of each command type.
 */
public class InputValidator {

    public InputValidator() {
    }

    /**
     * Checks if the 'todo' command has a non-empty description.
     *
     * @param commandTypeAndParams String array containing the command type and its parameters.
     * @throws MonaException If the description is empty.
     */
    public void checkTodoCommand(String[] commandTypeAndParams) throws MonaException {
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()) {
            throw new MonaException(Constants.EMPTY_FIELDS_ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the 'mark', 'unmark', or 'delete' command has a valid task number.
     *
     * @param commandTypeAndParams String array containing the command type and its parameters.
     * @throws MonaException If the task number is out of bounds. (eg. if there are 3 tasks in TaskList,
     * an invalid input would be -1, 0, 4, 5 etc.)
     * @throws NumberFormatException If the task number is not a 'parse-able' string. (eg. 'three')
     */
    public void checkMarkUnmarkDeleteCommand(String[] commandTypeAndParams) throws
            MonaException, NumberFormatException {

        // Checking if number provided can be converted to an integer
        try {
            Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]);
        } catch (NumberFormatException e) {
            throw new MonaException(Constants.INVALID_NUMBER_ERROR_MESSAGE);
        }

        // Checking if number provided is out of bounds (negative or more than the number of tasks stored)
        int markUnmarkIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
        if (markUnmarkIndex < 0 || markUnmarkIndex >= TaskList.noOfTasks) {
            throw new MonaException(Constants.INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the 'deadline' command has a non-empty description and deadline.
     *
     * @param commandTypeAndParams String array containing the command type and its parameters.
     * @throws MonaException If any of the required fields are empty.
     */
    public void checkDeadlineCommand(String[] commandTypeAndParams) throws MonaException {
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()
                || commandTypeAndParams[Constants.INDEX_DEADLINE].isEmpty()) {
            throw new MonaException(Constants.EMPTY_FIELDS_ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the 'event' command has a non-empty description, start value, and end value.
     *
     * @param commandTypeAndParams String array containing the command type and its parameters.
     * @throws MonaException If any of the required fields are empty.
     */
    public void checkEventCommand(String[] commandTypeAndParams) throws MonaException {
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()
                || commandTypeAndParams[Constants.INDEX_FROM_DATE].isEmpty()
                || commandTypeAndParams[Constants.INDEX_TO_DATE].isEmpty()) {
            throw new MonaException(Constants.EMPTY_FIELDS_ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the 'find' command has a non-empty description.
     *
     * @param commandTypeAndParams String array containing the command type and its parameters.
     * @throws MonaException If the description is empty.
     */
    public void checkFindCommand(String[] commandTypeAndParams) throws MonaException {
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()) {
            throw new MonaException(Constants.EMPTY_FIELDS_ERROR_MESSAGE);
        }
    }
}
