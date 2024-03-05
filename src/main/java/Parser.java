import java.util.ArrayList;

public class Parser {
    private static final int TODO_START_INDEX = 5;
    private static final int DEADLINE_START_INDEX = 9;
    private static final int EVENT_START_INDEX = 6;
    private static final int BY_INDEX = 4;
    private static final int FROM_INDEX = 6;
    private static final int TO_INDEX = 4;
    private static final int MARK_START_INDEX = 5;
    private static final int INDEX_OFFSET = 1;
    private static final int UNMARK_START_INDEX = 7;
    private static final int DELETE_START_INDEX = 7;

    public static String obtainTodoDescription(String userInput) {
        return userInput.substring(TODO_START_INDEX);
    }

    public static String obtainDeadlineDescription(String userInput) {
        return userInput.substring(DEADLINE_START_INDEX, userInput.indexOf("/by")) +
                "(by: " + userInput.substring(userInput.indexOf("/by") + BY_INDEX ) + ")";
    }

    public static String obtainEventDescription(String userInput) {
        return userInput.substring(EVENT_START_INDEX, userInput.indexOf(" /from")) + " (from: " +
                userInput.substring(userInput.indexOf("/from") + FROM_INDEX, userInput.indexOf(" /to")) +
                " to: " + userInput.substring(userInput.indexOf("/to") + TO_INDEX) + ")";
    }

    public static int obtainIndexToMark(String userInput) {
        return Integer.parseInt(userInput.substring(MARK_START_INDEX)) - INDEX_OFFSET;
    }

    public static int obtainIndexToUnmark(String userInput) {
        return Integer.parseInt(userInput.substring(UNMARK_START_INDEX)) - INDEX_OFFSET;
    }

    public static int obtainDeleteIndex(String userInput) {
        return Integer.parseInt(userInput.substring(DELETE_START_INDEX)) - INDEX_OFFSET;
    }

}
