package timl.utility;

/**
 * A Parser for user inputs
 */
public class TextParser {
    protected static final String SLASH_INDICATOR = "/";
    protected static final String DEADLINE_SIGN =  SLASH_INDICATOR + "by";
    protected static final String EVENT_START_INDICATOR = SLASH_INDICATOR + "from";
    protected static final String EVENT_END_INDICATOR = SLASH_INDICATOR + "to";

    /**
     * Returns the command word from the input
     *
     * @param input user Input string
     * @return a command word
     */
    public static String extractCommand(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    /**
     * Returns the message behind the command word from the input
     *
     * @param input user Input string
     * @return message after the command word
     */
    public static String extractMessage(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.substring(input.indexOf(" ")).trim(); //after command word
    }

    /**
     * Extracts the task name from a given message by locating the substring preceding a specified indicator.
     *
     * @param message the message from which th task name will be extracted
     * @return string representing the extracted task name
     */
    public static String extractTaskName(String message){
        return message.substring(0, message.indexOf(SLASH_INDICATOR)).trim();
    }

    public static String extractDeadlineTime(String message){
        return message.substring(message.indexOf(DEADLINE_SIGN)).replace(DEADLINE_SIGN, "").trim();
    }

    /**
     * Extracts event durations from a given message by locating substrings between specified start and end indicators.
     *
     * @param message represents message from which the durations are extracted
     * @return a String array. First element represents start duration, second element represents end duration
     */
    public static String[] extractEventTime(String message){
        String[] eventDurations = new String[2];
        int startIndex = message.indexOf(EVENT_START_INDICATOR);
        int endIndex = message.indexOf(EVENT_END_INDICATOR);
        eventDurations[0] = message.substring(startIndex, endIndex).replace(EVENT_START_INDICATOR, "").trim();
        eventDurations[1] = message.substring(endIndex).replace(EVENT_END_INDICATOR, "").trim();
        return eventDurations;
    }

}
