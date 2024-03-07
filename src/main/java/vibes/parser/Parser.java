package vibes.parser;

import vibes.common.ErrorMessages;
import vibes.exception.InvalidArgumentException;

/**
 * Parses user input.
 */
public class Parser {

    public static final String SPACE_CHAR = " ";
    public static final String SPLIT_BY = " /by ";
    public static final String SPLIT_FROM_OR_TO = " /from | /to ";
    public static final int SPLIT_TO_TWO = 2;
    public static final int SPLIT_TO_TWO_LENGTH = 2;
    public static final int SPLIT_TO_THREE = 3;
    public static final int SPLIT_TO_THREE_LENGTH = 3;
    public static final int COMMAND_INDEX = 0;
    public static final int TASK_NUMBER_INDEX = 1;
    public static final int TASK_NUMBER_OFFSET = 1;
    public static final int TODO_DESC_INDEX = 1;
    public static final int SECOND_PART_INDEX = 1;
    public static final int DEADLINE_DESC_INDEX = 0;
    public static final int DEADLINE_BY_INDEX = 1;
    public static final int EVENT_DESC_INDEX = 0;
    public static final int EVENT_FROM_INDEX = 1;
    public static final int EVENT_TO_INDEX = 2;
    public static final int KEYWORD_INDEX = 1;

    /**
     * Parses the full user input for a "Todo" task.
     *
     * @param userInput the full user input to be parsed
     * @return the description of the "Todo" task extracted from the user input
     * @throws InvalidArgumentException if the full user input does not contain the description
     */
    public static String parseTodo(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(SPACE_CHAR, SPLIT_TO_TWO);
        if (parts.length == SPLIT_TO_TWO_LENGTH) {
            return parts[TODO_DESC_INDEX];
        } else {
            throw new InvalidArgumentException(ErrorMessages.TODO_ARG_EMPTY);
        }
    }

    /**
     * Parses the full user input for a "Deadline" task.
     *
     * @param userInput the full user input to be parsed
     * @return an array containing the description and deadline of the "Deadline" task extracted from the user input
     * @throws InvalidArgumentException if the full user input does not contain the expected format or is empty
     */
    public static String[] parseDeadline(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(SPACE_CHAR, SPLIT_TO_TWO);
        if (parts.length == SPLIT_TO_TWO_LENGTH) {
            String[] taskParts = parts[SECOND_PART_INDEX].split(SPLIT_BY, SPLIT_TO_TWO);
            if(taskParts.length == SPLIT_TO_TWO_LENGTH) {
                String description = taskParts[DEADLINE_DESC_INDEX];
                String by = taskParts[DEADLINE_BY_INDEX];
                return new String[]{description, by};
            } else {
                throw new InvalidArgumentException(ErrorMessages.BY_DATE_NOT_FOUND);
            }
        } else {
            throw new InvalidArgumentException(ErrorMessages.DEADLINE_ARG_EMPTY);
        }
    }

    /**
     * Parses the full user input for an "Event" task.
     *
     * @param userInput the full user input to be parsed
     * @return an array containing the description, starting date, and ending date of the "Event" task extracted from the user input
     * @throws InvalidArgumentException if the user input does not contain the expected format or is empty
     */
    public static String[] parseEvent(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(SPACE_CHAR, SPLIT_TO_TWO);
        if (parts.length == SPLIT_TO_TWO_LENGTH) {
            String[] taskParts = parts[SECOND_PART_INDEX].split(SPLIT_FROM_OR_TO, SPLIT_TO_THREE);
            if (taskParts.length == SPLIT_TO_THREE_LENGTH) {
                String description = taskParts[EVENT_DESC_INDEX];
                String from = taskParts[EVENT_FROM_INDEX];
                String to = taskParts[EVENT_TO_INDEX];
                return new String[]{description, from, to};
            } else {
                throw new InvalidArgumentException(ErrorMessages.FROM_OR_TO_DATE_NOT_FOUND);
            }
        } else {
            throw new InvalidArgumentException(ErrorMessages.EVENT_ARG_EMPTY);
        }
    }

    /**
     * Parses the full user input into a task number.
     *
     * @param userInput the full user input to be parsed
     * @return the parsed task number
     * @throws InvalidArgumentException if the user input does not contain the task number
     */
    public static int parseTaskNumber(String userInput) throws InvalidArgumentException{
        String[] parts = userInput.split(SPACE_CHAR, SPLIT_TO_TWO);
        if (parts.length == SPLIT_TO_TWO_LENGTH){
            return Integer.parseInt(parts[TASK_NUMBER_INDEX]) - TASK_NUMBER_OFFSET;
        } else {
            throw new InvalidArgumentException(ErrorMessages.TASK_NUMBER_NOT_FOUND);
        }
    }

    /**
     * Parses the user input to extract the command.
     *
     * @param userInput the user input to be parsed
     * @return the extracted command in lowercase
     */
    public static String parseCommand(String userInput) {
        return userInput.split(SPACE_CHAR)[COMMAND_INDEX].toLowerCase();
    }

    public static String parseKeyword(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(SPACE_CHAR, SPLIT_TO_TWO);
        if (parts.length == SPLIT_TO_TWO_LENGTH){
            return parts[KEYWORD_INDEX];
        } else {
            throw new InvalidArgumentException(ErrorMessages.KEYWORD_NOT_FOUND);
        }
    }
}
