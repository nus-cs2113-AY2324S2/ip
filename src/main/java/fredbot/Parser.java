package fredbot;

public class Parser {
    private static final String INPUT_ARG_SEPARATOR = " ";
    private static final int MAX_INPUT_ARG = 2;
    private static final String EMPTY_STRING = "";
    private static final int INDEX_FIRST = 0;

    private static final String PREFIX_BY = "/by";
    private static final String PREFIX_FROM = "/from";
    private static final String PREFIX_TO = "/to";

    public Parser() {

    }

    public static String[] splitCommandWordAndArgs(String input) {
        final String[] split = input.split(INPUT_ARG_SEPARATOR, MAX_INPUT_ARG);
        return split.length == MAX_INPUT_ARG ? split : new String[]{split[INDEX_FIRST], EMPTY_STRING};
    }

    public static String[] splitDeadlineAndDate(String input) {
        final int indexOfByPrefix = input.indexOf(PREFIX_BY);
        String[] deadlineAndDate = new String[2];

        deadlineAndDate[0] = input.substring(0, indexOfByPrefix).trim();
        deadlineAndDate[1] = removePrefix(input.substring(indexOfByPrefix), PREFIX_BY).trim();
        return deadlineAndDate;
    }

    public static String[] splitEventAndDates(String input) {
        final int indexOfFromPrefix = input.indexOf(PREFIX_FROM);
        final int indexOfToPrefix = input.indexOf(PREFIX_TO);
        String[] eventAndDates = new String[3];

        eventAndDates[0] = input.substring(0, indexOfFromPrefix).trim();
        eventAndDates[1] = removePrefix(input.substring(indexOfFromPrefix, indexOfToPrefix), PREFIX_FROM).trim();
        eventAndDates[2] = removePrefix(input.substring(indexOfToPrefix), PREFIX_TO).trim();
        return eventAndDates;
    }

    private static String removePrefix(String s, String prefix) {
        return s.replace(prefix, "");
    }

    public static int getIndex(String taskNumber) {
        return Integer.parseInt(taskNumber) - 1;
    }
}
