package hachi.data;

import hachi.data.task.Task;

/**
 * Represent the error handling class for the chatbot Hachi.
 */

public class HachiException extends Exception{
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
    public HachiException (String errorMessage) {
        super(errorMessage);
    }

    public static void invalidInput() throws HachiException {
        throw new HachiException(INVALID_INPUT_MESSAGE);
    }

    public static void checkEmptyList(int numTasks) throws HachiException {
        if (numTasks == 0) {
            throw new HachiException(EMPTY_TASK_LIST_MESSAGE);
        }
    }

    public static void checkValidDescription (String line) throws HachiException {
        String[] userWords = line.split(" ");
        if (userWords.length == 1) {
            throw new HachiException(EMPTY_TASK_DESCRIPTION_MESSAGE);
        }
    }

    public static void checkDeadlineByDate (int indexOfBy) throws HachiException {
        if (indexOfBy == 2) {
            throw new HachiException(MISSING_DEADLINE_BY_DATE_MESSAGE);
        }
    }

    public static void checkEventDates (int indexOfStart, int indexOfEnd) throws HachiException {
        if (indexOfEnd == 2 || indexOfStart == 4) {
            throw new HachiException(MISSING_EVENT_START_END_DATE_MESSAGE);
        }
    }

    public static void checkOutOfBounds (int taskNumber) throws HachiException{
        int currentLastTask = Task.getTotalNumTasks();

        if (taskNumber > currentLastTask || taskNumber <= 0) {
            throw new HachiException(LIST_OUT_OF_BOUNDS_MESSAGE);
        }
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
