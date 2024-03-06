package tool;

import command.CommandType;
import exception.InputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    private static final String FIND_REGEX = "\\s*(/w|/t)\\s*.+";
    public static final String TIME_INPUT_PATTERN = "dd/MM/yyyy HHmm";
    public static final String TIME_OUTPUT_PATTERN = "MMM dd yyyy HH:mm";
    private static final DateTimeFormatter INPUT_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(TIME_INPUT_PATTERN);
    private static final DateTimeFormatter OUTPUT_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(TIME_OUTPUT_PATTERN);
    public static final String TIME_PREFIX = "/t";

    /**
     * Separates the command from the user input message
     * @param message typed message from the user input
     * @return String[] containing the command and the message
     */
    public static String[] separateCommand(String message) {
        int splitLimit = 2;
        String[] processedMessage = 
                message.toLowerCase().trim().split("\\s+", splitLimit);
        if (processedMessage.length != splitLimit) {
            return new String[] {processedMessage[0], ""};
        }
        return processedMessage;
    }

    /**
     * Decodes the user input message and returns the decoded message
     * @param type type of command received
     * @param message user input information about the command
     * @return String[] containing the decoded message
     * @throws InputException exception thrown when the user input does not match the format
     */
    public static String[] decodeInput(CommandType type, String message) throws InputException {
        switch (type) {
        case TODO:
            return decodeTodo(message);

        case DEADLINE:
            return decodeDeadline(message);

        case EVENT:
            return decodeEvent(message);

        case FIND:
            return decodeFind(message);

        case MARK:
        case UNMARK:
        case DELETE:
            return decodeIndex(message);

        default:
            return new String[] {""};

        }
    }

    /**
     * Decodes the user input information for the todo command
     * @param message user input information about the todo command
     * @return String[] containing the decoded message for the todo command
     * @throws InputException exception thrown when the user input is blank or does not match the format
     */
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

    /**
     * Decodes the user input information for the deadline command
     * @param message user input information about the deadline command
     * @return String[] containing the decoded message for the deadline command
     * @throws InputException exception thrown when the user input does not match the format
     */
    private static String[] decodeDeadline(String message) throws InputException {
        if (!message.matches(DEADLINE_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.DEADLINE);
        }
        int indexOfDeadlinePrefix = message.indexOf(DEADLINE_PREFIX);
        String[] decoded = new String[DEADLINE_INFO_COUNT];
        decoded[TASK_NAME_INDEX] = message.substring(0, indexOfDeadlinePrefix).trim();
        decoded[DUE_DATE_INDEX] =
                removePrefixMark(message.substring(indexOfDeadlinePrefix).trim(), DEADLINE_PREFIX)
                .trim();

        for (String msg : decoded) {
            if (msg.matches(BLANK)) {
                throw new InputException(ResponseManager.BLANK_MSG_ERROR);
            }
        }
        decoded[DUE_DATE_INDEX] = parseDate(decoded[DUE_DATE_INDEX]);
        return decoded;
    }

    /**
     * Decodes the user input information for the event command
     * @param message user input information about the event command
     * @return String[] containing the decoded message for the event command
     * @throws InputException exception thrown when the user input does not match the format
     */
    private static String[] decodeEvent(String message) throws InputException {
        if (!message.matches(EVENT_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.EVENT);
        }
        int indexOfFrom = message.indexOf(EVENT_START_PREFIX);
        int indexOfTo = message.indexOf(EVENT_END_PREFIX);

        String[] decoded = getFromNTo(message, indexOfFrom, indexOfTo);
        decoded[START_DATE_INDEX] = parseDate(decoded[START_DATE_INDEX]);
        decoded[END_DATE_INDEX] = parseDate(decoded[END_DATE_INDEX]);
        return decoded;
    }
    
    /**
     * Decodes the user input information for start time and end time of the event
     * @param message user input information about the event command
     * @param indexOfFrom index of the start time prefix
     * @param indexOfTo index of the end time prefix
     * @return String[] containing the decoded start time and end time for the event command
     * @throws InputException exception thrown when the user input does not match the format
     */
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

    /**
     * Decodes the user input command for the index of the target task
     * @param message user input information about the index of the target task
     * @return String[] containing the decoded index of the target task
     * @throws InputException exception thrown when the user input is blank or does not match the format
     */
    private static String[] decodeIndex(String message) throws InputException {
        if (message.matches(BLANK)) {
            throw new InputException(ResponseManager.BLANK_MSG_ERROR);
        }
        if (!message.matches(NUMERIC)) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        return new String[] {message};
    }

    /**
     * Removes the prefix mark from the user input message
     * @param rawText user input message
     * @param sign prefix mark to be removed
     * @return String containing the user input message without the prefix mark
     */
    private static String removePrefixMark(String rawText, String sign) {
        return rawText.replace(sign, "");
    }

    /**
     * Decodes the user input message for the find command
     * @param message user input information about the find command
     * @return String[] containing the decoded message for the find command
     * @throws InputException exception thrown when the user input does not match the format
     */
    private static String[] decodeFind(String message) throws InputException {
        if (!message.matches(FIND_REGEX)) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE);
        }

        int splitLimit = 2;
        String[] typeAndKeyword = message.trim().split("\\s+", splitLimit);
        if (typeAndKeyword.length != splitLimit) {
            throw new InputException(ResponseManager.FORMAT_ERROR_MESSAGE);
        }

        if (typeAndKeyword[0].equals(TIME_PREFIX)) {
            typeAndKeyword[1] = parseDate(typeAndKeyword[1]);
        }
        return typeAndKeyword;
    }

    /**
     * Parses the date and time from the user input message and converts it to a readable format
     * @param input user input message containing the date and time
     * @return String containing the parsed date and time
     * @throws InputException exception thrown when the user input does not match the format
     */    
    private static String parseDate (String input) throws InputException {
        int splitLimit = 2;
        String[] inputTime = input.split("\\s+", splitLimit);
        if (inputTime.length != splitLimit) {
            throw new InputException(ResponseManager.DATE_FORMAT_ERROR);
        }
        String formattedTime = inputTime[0] + " " + inputTime[1];
        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(formattedTime, INPUT_TIME_FORMATTER);
        } catch (DateTimeParseException error) {
            throw new InputException(ResponseManager.DATE_FORMAT_ERROR);
        }
        return dateTime.format(OUTPUT_TIME_FORMATTER);
    }

    /**
     * Decodes the saved data from the file
     * @param data saved data from the file
     * @return String[] containing the decoded saved data
     */
    public static String[] decodeSavedData(String data) {
        return data.split(" / ");
    }
}
