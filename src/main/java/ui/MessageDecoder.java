package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.CommandType;
import exception.ZukeException;

/**
 * The MessageDecoder class decodes the user input message and 
 * returns the decoded message.
 */
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
    private static final String DATE_REGEX = "\\d{2}/\\d{2}/\\d{4}";
    private static final String TIME_REGEX = "\\d{4}";
    private static final DateTimeFormatter INPUT_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    public static final String TIME_PREFIX = "/t";
    public static final String FIND_FORMAT = "\nfind /w <keyword> or find /t <time>\n";
    public static final String EVENT_FORMAT = "\nevent <task name> /from <start time> /to <end time>\n";
    public static final String DEADLINE_FORMAT = "\ndeadline /by <due date>\n";
    public static final String TODO_FORMAT = "\ntodo <task name>\n";
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HHmm");

    /**
     * Separates the command from the user input message.
     * 
     * @param message typed message from the user input.
     * @return String[] containing the command and the message
     * where index 0 is the command and index 1 is the information.
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
     * Decodes the user input message and returns the decoded message.
     * 
     * @param type type of command received.
     * @param message user input information about the command.
     * @return String[] containing the decoded details regarding the command.
     * @throws ZukeException exception thrown when the user input does not match the format.
     */
    public static String[] decodeInput(CommandType type, String message) throws ZukeException {
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
     * Decodes the user input information for the todo command.
     * 
     * @param message user input information about the todo command.
     * @return String[] containing the decoded details for the todo command.
     * @throws ZukeException exception thrown when the user input is blank or does not match the format.
     */
    private static String[] decodeTodo(String message) throws ZukeException {
        if (message.matches(BLANK)) {
            throw new ZukeException(ResponseManager.BLANK_MSG_ERROR);
        }
        if (!message.matches(TODO_REGEX)) {
            throw new ZukeException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.TODO +
                    TODO_FORMAT);
        }

        return new String[] {message};
    }

    /**
     * Decodes the user input information for the deadline command.
     * 
     * @param message user input information about the deadline command.
     * @return String[] containing the decoded details for the deadline command.
     * @throws ZukeException exception thrown when the user input does not match the format.
     */
    private static String[] decodeDeadline(String message) throws ZukeException {
        if (!message.matches(DEADLINE_REGEX)) {
            throw new ZukeException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.DEADLINE +
                    DEADLINE_FORMAT);
        }
        int indexOfDeadlinePrefix = message.indexOf(DEADLINE_PREFIX);
        String[] decoded = new String[DEADLINE_INFO_COUNT];
        decoded[TASK_NAME_INDEX] = message.substring(0, indexOfDeadlinePrefix).trim();
        decoded[DUE_DATE_INDEX] =
                removePrefixMark(message.substring(indexOfDeadlinePrefix).trim(), DEADLINE_PREFIX)
                .trim();

        for (String msg : decoded) {
            if (msg.matches(BLANK)) {
                throw new ZukeException(ResponseManager.BLANK_MSG_ERROR);
            }
        }
        decoded[DUE_DATE_INDEX] = decodeDateNTime(decoded[DUE_DATE_INDEX]);
        return decoded;
    }

    /**
     * Decodes the user input information for the event command.
     * 
     * @param message user input information about the event command.
     * @return String[] containing the decoded details for the event command.
     * @throws ZukeException exception thrown when the user input does not match the format.
     */
    private static String[] decodeEvent(String message) throws ZukeException {
        if (!message.matches(EVENT_REGEX)) {
            throw new ZukeException(ResponseManager.FORMAT_ERROR_MESSAGE +
                    ResponseManager.EVENT +
                    EVENT_FORMAT);
        }
        int indexOfFrom = message.indexOf(EVENT_START_PREFIX);
        int indexOfTo = message.indexOf(EVENT_END_PREFIX);

        String[] decoded = getFromNTo(message, indexOfFrom, indexOfTo);
        decoded[START_DATE_INDEX] = decodeDateNTime(decoded[START_DATE_INDEX]);
        decoded[END_DATE_INDEX] = decodeDateNTime(decoded[END_DATE_INDEX]);
        LocalDateTime start = LocalDateTime.parse(decoded[START_DATE_INDEX], OUTPUT_TIME_FORMATTER);
        LocalDateTime end = LocalDateTime.parse(decoded[END_DATE_INDEX], OUTPUT_TIME_FORMATTER);
        if (start.isAfter(end)) {
            throw new ZukeException(ResponseManager.TIME_PARADOX_MSG);
        }
        return decoded;
    }
    
    /**
     * Decodes the user input information for start time and end time of the event.
     * 
     * @param message user input information about the event command.
     * @param indexOfFrom index of the start time prefix.
     * @param indexOfTo index of the end time prefix.
     * @return String[] containing the decoded start time and end time for the event command.
     * @throws ZukeException exception thrown when the user input does not match the format.
     */
    private static String[] getFromNTo(String message,
            int indexOfFrom, int indexOfTo) throws ZukeException {
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
                throw new ZukeException(ResponseManager.BLANK_MSG_ERROR);
            }
        }
        return decoded;
    }

    /**
     * Decodes the user input for the index of the target task.
     * 
     * @param message user input information about the index of the target task.
     * @return String[] containing the decoded index of the target task.
     * @throws ZukeException exception thrown when the user input is blank or does not match the format.
     */
    private static String[] decodeIndex(String message) throws ZukeException {
        if (message.matches(BLANK)) {
            throw new ZukeException(ResponseManager.BLANK_MSG_ERROR);
        }
        if (!message.matches(NUMERIC)) {
            throw new ZukeException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        return new String[] {message};
    }

    /**
     * Removes the prefix mark from the user input message.
     * 
     * @param rawText user input message.
     * @param sign prefix mark to be removed.
     * @return String containing the user input message without the prefix mark.
     */
    private static String removePrefixMark(String rawText, String sign) {
        return rawText.replace(sign, "");
    }

    /**
     * Decodes the user input message for the find command.
     * 
     * @param message user input information about the find command.
     * @return String[] containing the decoded details for the find command.
     * @throws ZukeException exception thrown when the user input does not match the format.
     */
    private static String[] decodeFind(String message) throws ZukeException {
        if (!message.matches(FIND_REGEX)) {
            throw new ZukeException(ResponseManager.FORMAT_ERROR_MESSAGE + CommandType.FIND +
                    FIND_FORMAT);
        }

        int splitLimit = 2;
        String[] typeAndKeyword = message.trim().split("\\s+", splitLimit);
        if (typeAndKeyword.length != splitLimit) {
            throw new ZukeException(ResponseManager.FORMAT_ERROR_MESSAGE + CommandType.FIND +
                    FIND_FORMAT);
        }

        if (typeAndKeyword[0].equals(TIME_PREFIX)) {
            typeAndKeyword[1] = decodeDateNTime(typeAndKeyword[1]);
        }
        return typeAndKeyword;
    }

    /**
     * Parses the date and time from the user input message and 
     * converts it to a readable format.
     * 
     * @param input user input message containing the date and time.
     * @return String containing the parsed date and time.
     * @throws ZukeException exception thrown when the user input does not match the format.
     */    
    private static String decodeDateNTime(String input) throws ZukeException {
        int splitLimit = 2;
        String[] inputTime = input.split("\\s+", splitLimit);
        if (inputTime.length != splitLimit) {
            return decodeDateOrTime(inputTime[0]);
        }

        String formattedTime = inputTime[0] + " " + inputTime[1];
        try {
            return LocalDateTime.parse(formattedTime, INPUT_TIME_FORMATTER)
                    .format(OUTPUT_TIME_FORMATTER);
        } catch (DateTimeParseException error) {
            throw new ZukeException(ResponseManager.DATE_FORMAT_ERROR);
        }
    }

    /**
     * Decodes the date or time from the user input message 
     * and converts it to a readable format when user only inputs date or time.
     * 
     * @param input user input message containing the date or time.
     * @return String containing the decoded and formatted date or time.
     * @throws ZukeException exception thrown when the user input does not match the format.
     */
    private static String decodeDateOrTime(String input) throws ZukeException {
        if (!input.matches(DATE_REGEX) && !input.matches(TIME_REGEX)) {
            throw new ZukeException(ResponseManager.DATE_FORMAT_ERROR);
        }

        String today = LocalDate.now().format(DATE_FORMATTER);
        String currTime = LocalTime.now().format(TIME_FORMATTER);
        try {
            if (input.matches(DATE_REGEX)) {
                return LocalDateTime.parse(input + " " + currTime, INPUT_TIME_FORMATTER)
                        .format(OUTPUT_TIME_FORMATTER);
            }
            return LocalDateTime.parse(today + " " + input, INPUT_TIME_FORMATTER)
                    .format(OUTPUT_TIME_FORMATTER);
        } catch (DateTimeParseException error) {
            throw new ZukeException(ResponseManager.DATE_FORMAT_ERROR);
        }
    }

    /**
     * Decodes the saved data from a text file.
     * 
     * @param data saved data from the file.
     * @return String[] containing the decoded saved data.
     */
    public static String[] decodeSavedData(String data) {
        return data.split(" \\| ");
    }
}
