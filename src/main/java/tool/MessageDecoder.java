package tool;

import command.CommandType;
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
        String[] processedMessage = 
                message.toLowerCase().trim().split("\\s+", splitLimit);
        if (processedMessage.length != splitLimit) {
            return new String[] {processedMessage[0], ""};
        }
        return processedMessage;
    }

    public static String[] decodeInput(CommandType type, String message) throws InputException {
        switch (type) {
        case TODO:
            return decodeTodo(message);

        case DEADLINE:
            return decodeDeadline(message);

        case EVENT:
            return decodeEvent(message);

        case MARK:
        case UNMARK:
        case DELETE:
            return decodeIndex(message);

        default:
            return new String[] {""};

        }
    }

    private static String[] decodeTodo(String message) throws InputException {
        if (message.matches(BLANK)) {
            throw new InputException(ResponseManager.BLANK_MSG_ERROR);
        }
        if (!message.matches(TODO_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.TODO);
        }

        return new String[] {message};
    }

    private static String[] decodeDeadline(String message) throws InputException {
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

    private static String[] decodeEvent(String message) throws InputException {
        if (!message.matches(EVENT_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.EVENT);
        }
        int indexOfFrom = message.indexOf(EVENT_START_PREFIX);
        int indexOfTo = message.indexOf(EVENT_END_PREFIX);

        return getFromNTo(message, indexOfFrom, indexOfTo);
    }

    private static String[] getFromNTo(String message,
            int indexOfFrom, int indexOfTo) throws InputException {
        String[] decoded = new String[EVENT_INFO_COUNT];
        if (indexOfTo > indexOfFrom) {
            decoded[TASK_NAME_INDEX] = message.substring(0, indexOfFrom).trim();
            decoded[START_DATE_INDEX] = removePrefixMark(
                    message.substring(indexOfFrom, indexOfTo), EVENT_START_PREFIX).trim();
            decoded[END_DATE_INDEX] = removePrefixMark(message.substring(indexOfTo).trim(),
                    EVENT_END_PREFIX).trim();
        } else {
            decoded[TASK_NAME_INDEX] = message.substring(0, indexOfTo).trim();
            decoded[END_DATE_INDEX] = removePrefixMark(
                    message.substring(indexOfTo, indexOfFrom), EVENT_END_PREFIX).trim();
            decoded[START_DATE_INDEX] = removePrefixMark(message.substring(indexOfFrom).trim(),
                    EVENT_START_PREFIX).trim();
        }

        for (String msg : decoded) {
            if (msg.matches(BLANK)) {
                throw new InputException(ResponseManager.BLANK_MSG_ERROR);
            }
        }
        return decoded;
    }

    private static String[] decodeIndex(String message) throws InputException {
        if (message.matches(BLANK)) {
            throw new InputException(ResponseManager.BLANK_MSG_ERROR);
        }
        if (!message.matches(NUMERIC)) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        return new String[] {message};
    }

    private static String removePrefixMark(String rawText, String sign) {
        return rawText.replace(sign, "");
    }
}
