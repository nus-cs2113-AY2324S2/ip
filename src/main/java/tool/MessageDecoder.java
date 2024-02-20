package tool;

import exception.InputException;
public class MessageDecoder {
    private static final String DEADLINE_PREFIX = "/by";
    private static final String EVENT_START_PREFIX = "/from";
    private static final String EVENT_END_PREFIX = "/to";
    private static final String TODO_REGEX = ".+";
    private static final String DEADLINE_REGEX = ".+/by\\s*.+";
    private static final String EVENT_REGEX =
            ".+(/from|/to)\\s*.+(/from|/to)\\s*.+";
    private static final String BLANK = "\\s*";
    private static final int DEADLINE_INFO_COUNT = 2;
    private static final int EVENT_INFO_COUNT = 3;
    private static final int TASK_NAME_INDEX = 0;
    private static final int DUE_DATE_INDEX = 1;
    private static final int START_DATE_INDEX = 1;
    private static final int END_DATE_INDEX = 2;
    private static final String NUMERIC = "\\d+";

    public static String[] separateCommand(String message) {
        int splitLimit = 2;
        String[] processedMessage = message.trim().split("\\s+", splitLimit);
        if (processedMessage.length != splitLimit) {
            return new String[] {processedMessage[0], ""};
        }
        return processedMessage;
    }

    public static String decodeTodo(String message) throws InputException {
        if (message.matches(BLANK)) {
            throw new InputException(ResponseManager.BLANK_MSG_ERROR);
        }
        if (!message.matches(TODO_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.TODO);
        }

        return message;
    }

    public static String[] decodeDeadline(String message) throws InputException {
        if (!message.matches(DEADLINE_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.DEADLINE);
        }
        int indexOfDeadlinePrefix = message.indexOf(DEADLINE_PREFIX);
        String[] decoded = new String[DEADLINE_INFO_COUNT];
        decoded[TASK_NAME_INDEX] = message.substring(0, indexOfDeadlinePrefix).trim();
        decoded[DUE_DATE_INDEX] = removePrefixMark(message.substring(indexOfDeadlinePrefix).trim(),
                DEADLINE_PREFIX).trim();

        for (String msg : decoded) {
            if (msg.matches(BLANK)) {
                throw new InputException(ResponseManager.BLANK_MSG_ERROR);
            }
        }
        return decoded;
    }

    public static String[] decodeEvent(String message) throws InputException {
        if (!message.matches(EVENT_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.EVENT);
        }
        int indexOfStartPrefix = message.indexOf(EVENT_START_PREFIX);
        int indexOfEndPrefix = message.indexOf(EVENT_END_PREFIX);
        int endOfTaskName = Math.min(indexOfStartPrefix, indexOfEndPrefix);
        String[] decoded = new String[EVENT_INFO_COUNT];

        decoded[TASK_NAME_INDEX] = message.substring(0, endOfTaskName).trim();

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

        for (String msg : decoded) {
            if (msg.matches(BLANK)) {
                throw new InputException(ResponseManager.BLANK_MSG_ERROR);
            }
        }
        return decoded;
    }

    public static int decodeIndex(String message) throws InputException {
        if (message.matches(BLANK)) {
            throw new InputException(ResponseManager.BLANK_MSG_ERROR);
        }
        if (!message.matches(NUMERIC)) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        return Integer.parseInt(message);
    }

    public static String removePrefixMark(String rawText, String sign) {
        return rawText.replace(sign, "");
    }
}
