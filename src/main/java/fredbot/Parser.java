package fredbot;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private static final String INPUT_ARG_SEPARATOR = " ";
    private static final int MAX_INPUT_ARG = 2;
    private static final String EMPTY_STRING = "";
    private static final int INDEX_FIRST = 0;

    private static final String PREFIX_BY = "/by";
    private static final String PREFIX_FROM = "/from";
    private static final String PREFIX_TO = "/to";

    /**
     * Constructs the parser object.
     */
    public Parser() {

    }

    /**
     * Splits the user input into the command word and the command arguments.
     *
     * @param input User input.
     * @return Array of command word and arguments.
     */
    public String[] splitCommandWordAndArgs(String input) {
        final String[] split = input.split(INPUT_ARG_SEPARATOR, MAX_INPUT_ARG);
        return split.length == MAX_INPUT_ARG ? split : new String[]{split[INDEX_FIRST], EMPTY_STRING};
    }

    /**
     * Splits the Deadline details into its description and its date.
     *
     * @param input Deadline details input by the user.
     * @return Array of deadline description and date.
     */
    public String[] splitDeadlineAndDate(String input) {
        final int indexOfByPrefix = input.indexOf(PREFIX_BY);
        String[] deadlineAndDate = new String[2];

        deadlineAndDate[0] = input.substring(0, indexOfByPrefix).trim();
        deadlineAndDate[1] = removePrefix(input.substring(indexOfByPrefix), PREFIX_BY).trim();
        return deadlineAndDate;
    }

    /**
     * Splits the Event details into its description and its dates.
     *
     * @param input Event details input by the user.
     * @return Array of event description and dates.
     */
    public String[] splitEventAndDates(String input) {
        final int indexOfFromPrefix = input.indexOf(PREFIX_FROM);
        final int indexOfToPrefix = input.indexOf(PREFIX_TO);
        String[] eventAndDates = new String[3];

        eventAndDates[0] = input.substring(0, indexOfFromPrefix).trim();
        eventAndDates[1] = removePrefix(input.substring(indexOfFromPrefix, indexOfToPrefix), PREFIX_FROM).trim();
        eventAndDates[2] = removePrefix(input.substring(indexOfToPrefix), PREFIX_TO).trim();
        return eventAndDates;
    }

    /**
     * Removes command prefix from the user input.
     *
     * @param s User input.
     * @param prefix Prefix to be removed.
     * @return Input with the prefix removed.
     */
    private static String removePrefix(String s, String prefix) {
        return s.replace(prefix, "");
    }

    /**
     * Finds the index of the task in the array list.
     *
     * @param taskNumber Index of the task input by the user.
     * @return The index od the task in the task list.
     */
    public int getIndex(String taskNumber) {
        return Integer.parseInt(taskNumber) - 1;
    }
}
