public class InputParser {

    // Parses user input

    // These are the prefix strings to define the data type of a command parameter
    private static final String TASK_DATA_PREFIX_DEADLINE = "/by ";
    private static final String TASK_DATA_PREFIX_EVENT_START = "/from ";
    private static final String TASK_DATA_PREFIX_EVENT_END = "/to ";

    public static String[] parseInput(String userInput){
        final String regexMatcher = TASK_DATA_PREFIX_DEADLINE + "|"
                + TASK_DATA_PREFIX_EVENT_START + "|" + TASK_DATA_PREFIX_EVENT_END;
        return userInput.trim().split(regexMatcher);
    }
}
