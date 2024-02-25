package yuki;

/**
 * Methods to parse input by the user.
 */
public class InputParser {

    // Prefix strings to identify dates inputted by user.
    private static final String TASK_DATA_PREFIX_DEADLINE = "/by ";
    private static final String TASK_DATA_PREFIX_EVENT_START = "/from ";
    private static final String TASK_DATA_PREFIX_EVENT_END = "/to ";
    static final String regexMatcher = TASK_DATA_PREFIX_DEADLINE + "|"
            + TASK_DATA_PREFIX_EVENT_START + "|" + TASK_DATA_PREFIX_EVENT_END;
    private static final int INDEX_COMMAND = 0;

    /**
     * Returns command entered by the user, expected to be at beginning of string.
     * Error handling in TaskList.java
     *
     * @param userInput input from the user in the command line.
     */
    public static String parseCommand(String userInput) {
        return userInput.trim().split(" ")[INDEX_COMMAND];
    }

    /**
     * Returns details out input entered by the user, splitted into description, and any dates.
     * Error handling in TaskList.java
     *
     * @param userInput input from the user in the command line.
     */
    public static String[] parseInput(String userInput){
        return userInput.trim().split(regexMatcher);
    }
}
