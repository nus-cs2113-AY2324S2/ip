package yuki;

public class InputParser {

    // Prefix strings to define the data type of a command parameter
    private static final String TASK_DATA_PREFIX_DEADLINE = "/by ";
    private static final String TASK_DATA_PREFIX_EVENT_START = "/from ";
    private static final String TASK_DATA_PREFIX_EVENT_END = "/to ";
    static final String regexMatcher = TASK_DATA_PREFIX_DEADLINE + "|"
            + TASK_DATA_PREFIX_EVENT_START + "|" + TASK_DATA_PREFIX_EVENT_END;

    public static String parseCommand(String userInput) {
        return userInput.trim().split(" ")[0];
    }

    public static String[] parseInput(String userInput){
        return userInput.trim().split(regexMatcher);
    }
}
