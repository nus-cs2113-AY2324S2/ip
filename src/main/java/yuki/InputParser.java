package yuki;

public class InputParser {

    // Prefix strings to define the data type of a command parameter
    private static final String TASK_DATA_PREFIX_DEADLINE = "/by ";
    private static final String TASK_DATA_PREFIX_EVENT_START = "/from ";
    private static final String TASK_DATA_PREFIX_EVENT_END = "/to ";
    static final String regexMatcher = TASK_DATA_PREFIX_DEADLINE + "|"
            + TASK_DATA_PREFIX_EVENT_START + "|" + TASK_DATA_PREFIX_EVENT_END;
    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_DESCRIPTION= 1;

    public static String parseCommand(String userInput) {
        return userInput.trim().split(" ")[INDEX_COMMAND];
    }

    public static String parseDescription(String userInput) {
        return userInput.trim().split(" ")[INDEX_DESCRIPTION];
    }

    public static String[] parseInput(String userInput){
        return userInput.trim().split(regexMatcher);
    }
}
