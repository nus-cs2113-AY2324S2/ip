package mona.input;

import mona.exception.MonaException;
import mona.util.Constants;
import mona.manager.TaskManager;

public class InputValidator {
    public InputValidator() {
    }
    public void checkTodoCommand(String[] commandTypeAndParams) throws MonaException {
        // Checking if description field is empty
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()) {
            throw new MonaException("OOPS!!! The description of a todo cannot be empty");
        }
    }
    public void checkMarkUnmarkDeleteCommand(String[] commandTypeAndParams) throws
            MonaException, NumberFormatException {
        // Checking if description (number) field is empty
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()) {
            throw new MonaException("OOPS!!! The description of this command cannot be empty");
        }

        // Checking if number provided can be converted to an integer
        try {
            Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]);
        } catch (NumberFormatException e) {
            throw new MonaException("No number detected! Try again!");
        }

        // Checking if number provided is out of bounds (negative or more than the number of tasks stored)
        int markUnmarkIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
        if (markUnmarkIndex < 0 || markUnmarkIndex >= TaskManager.noOfTasks) {
            throw new MonaException("OOPS! Number doesn't correspond to a stored task. Try again!");
        }
    }
    public void checkDeadlineCommand(String[] commandTypeAndParams) throws MonaException {
        // Checking if description field OR if deadline field is empty
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()
                || commandTypeAndParams[Constants.INDEX_DEADLINE].isEmpty()) {
            throw new MonaException("OOPS!!! Missing fields detected. Try again!");
        }
    }
    public void checkEventCommand(String[] commandTypeAndParams) throws MonaException {
        //Checking if description field OR /to field OR /from field is empty
        if (commandTypeAndParams[Constants.INDEX_DESCRIPTION].isEmpty()
                || commandTypeAndParams[Constants.INDEX_FROM_DATE].isEmpty()
                || commandTypeAndParams[Constants.INDEX_TO_DATE].isEmpty()) {
            throw new MonaException("OOPS!! Missing fields detected. Try again!");
        }
    }
}
