package sayo;

/**
 * This class handles parsing of user input commands to extract relevant information.
 */
public class Parser {
    
    /**
     * Parses the user input to get the index for marking a task as done.
     *
     * @param input The user input string.
     * @return The index of the task to be marked.
     */
    public static int getIndexForMark(String input) {
        return Integer.parseInt(input.substring(5)) - 1;
    }

    /**
     * Parses the user input to get the index for unmarking a task.
     *
     * @param input The user input string.
     * @return The index of the task to be unmarked.
     */
    public static int getIndexForUnmark(String input) {
        return Integer.parseInt(input.substring(7)) - 1;
    }

    /**
     * Extracts the todo description from the user input.
     *
     * @param input The user input string.
     * @return The todo description.
     */
    public static String getTodoDescription(String input) {
        return input.substring(5).trim();
    }

    /**
     * Extracts the deadline description from the user input.
     *
     * @param input The user input string.
     * @param byIndex The index where '/by' is found in the input string.
     * @return The deadline description.
     */    
    public static String getDeadlineDescription(String input, int byIndex) {
        return input.substring(9, byIndex).trim();
    }

    /**
     * Extracts the deadline 'by' part from the user input.
     *
     * @param input The user input string.
     * @param byIndex The index where '/by' is found in the input string.
     * @return The 'by' part of the deadline.
     */
    public static String getDeadlineBy(String input, int byIndex) {
        return input.substring(byIndex + 4).trim();
    }

    /**
     * Extracts the index of the event 'from' part from the user input.
     *
     * @param input The user input string.
     * @param byIndex The index where '/from' is found in the input string.
     * @return The 'from' part of the event.
     */
    public static int getEventFromIndex(String input) {
        return input.indexOf("/from");
    }

    /**
     * Extracts the index of the event 'to' part from the user input.
     *
     * @param input The user input string.
     * @return The 'to' part of the event.
     */
    public static int getEventToIndex(String input) {
        return input.indexOf("/to");
    }

    /**
     * Extracts the event description from the user input.
     *
     * @param input The user input string.
     * @param fromIndex The index where '/from' is found in the input string.
     * @return The event description.
     */    
    public static String getEventDescription(String input, int fromIndex) {
        return input.substring(6, fromIndex).trim();
    }

    /**
     * Extracts the event '/from' part from the user input.
     *
     * @param input The user input string.
     * @param fromIndex The index where '/from' is found in the input string.
     * @param toIndex The index where '/to' is found in the input string.
     * @return The '/from' part of the deadline.
     */
    public static String getEventStart(String input, int fromIndex, int toIndex) {
        return input.substring(fromIndex + 6, toIndex).trim();
    }

    /**
     * Extracts the event '/to' part from the user input.
     *
     * @param input The user input string.
     * @param toIndex The index where '/to' is found in the input string.
     * @return The '/to' part of the deadline.
     */
    public static String getEventEnd(String input, int toIndex) {
        return input.substring(toIndex + 4).trim();
    }

    /**
     * Parses the user input to get the index for deleting a task.
     *
     * @param input The user input string.
     * @return The index of the task to be deleted.
     */
    public static int getDeleteIndex(String input) {
        return Integer.parseInt(input.substring(7).trim()) - 1;
    }
    

}
