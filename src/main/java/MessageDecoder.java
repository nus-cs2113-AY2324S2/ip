public class MessageDecoder {
    private static final String DEADLINE_PREFIX = "/by";
    private static final String EVENT_START_PREFIX = "/from";
    private static final String EVENT_END_PREFIX = "/to";
    private static final int DEADLINE_INFO_COUNT = 2;
    private static final int EVENT_INFO_COUNT = 3;
    private static final int TASK_NAME_INDEX = 0;
    private static final int DUE_DATE_INDEX = 1;
    private static final int START_DATE_INDEX = 1;
    private static final int END_DATE_INDEX = 2;

    public static String[] separateCommand(String message) {
        return message.trim().split("\\s+", 2);
    }
    public static String[] decodeDeadline(String message) {
        int indexOfDeadlinePrefix = message.indexOf(DEADLINE_PREFIX);
        String[] decoded = new String[DEADLINE_INFO_COUNT];
        decoded[TASK_NAME_INDEX] = message.substring(0, indexOfDeadlinePrefix).trim();
        decoded[DUE_DATE_INDEX] = removePrefixMark(message.substring(indexOfDeadlinePrefix).trim(),
                DEADLINE_PREFIX).trim();
        return decoded;
    }

    public static String[] decodeEvent(String message) {
        int indexOfStartPrefix = message.indexOf(EVENT_START_PREFIX);
        int indexOfEndPrefix = message.indexOf(EVENT_END_PREFIX);
        String[] decoded = new String[EVENT_INFO_COUNT];
        decoded[TASK_NAME_INDEX] = message.substring(0, indexOfStartPrefix).trim();

        if (indexOfEndPrefix > indexOfStartPrefix) {
            decoded[START_DATE_INDEX] = removePrefixMark(
                    message.substring(indexOfStartPrefix, indexOfEndPrefix).trim(), EVENT_START_PREFIX).trim();
            decoded[END_DATE_INDEX] = removePrefixMark(message.substring(indexOfEndPrefix).trim(),
                    EVENT_END_PREFIX).trim();
            return decoded;
        }
        decoded[END_DATE_INDEX] = removePrefixMark(
                message.substring(indexOfEndPrefix, indexOfStartPrefix).trim(), EVENT_END_PREFIX).trim();
        decoded[START_DATE_INDEX] = removePrefixMark(message.substring(indexOfStartPrefix).trim(),
                EVENT_START_PREFIX).trim();
        return decoded;
    }

    public static String removePrefixMark(String rawText, String sign) {
        return rawText.replace(sign, "");
    }
}
