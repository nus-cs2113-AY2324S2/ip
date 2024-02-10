public class InputParser {
    protected static final int GARBAGE_INT = -69;
    protected static final String FLAG_INDICATOR = "/";
    protected static final String DEADLINE_FLAG = "/by";
    protected static final String EVENT_START_FLAG = "/from";
    protected static final String EVENT_END_FLAG = "/to";


    public static String getCommand(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    public static String getMessage(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.substring(input.indexOf(" ")).trim();
    }

    public static String getTaskName(String message) {
        if (!message.contains(FLAG_INDICATOR)) {
            throw new IllegalArgumentException();
        }
        return message.substring(0, message.indexOf(FLAG_INDICATOR)).trim();
    }

    public static String getDeadlineTime(String message) {
        if (!message.contains(DEADLINE_FLAG)) {
            throw new IllegalArgumentException();
        }
        return message.substring(message.indexOf(DEADLINE_FLAG)).replace(DEADLINE_FLAG, "").trim();
    }

    public static String[] getEventTime(String message) {
        if (!message.contains(EVENT_START_FLAG) || !message.contains(EVENT_END_FLAG)) {
            throw new IllegalArgumentException();
        }
        String[] eventDurations = new String[2];
        int startIndex = message.indexOf(EVENT_START_FLAG);
        int endIndex = message.indexOf(EVENT_END_FLAG);
        eventDurations[0] = message.substring(startIndex, endIndex).replace(EVENT_START_FLAG, "").trim();
        eventDurations[1] = message.substring(endIndex).replace(EVENT_END_FLAG, "").trim();
        return eventDurations;
    }
}
