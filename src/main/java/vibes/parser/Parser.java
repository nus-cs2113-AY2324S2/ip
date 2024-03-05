package vibes.parser;

import vibes.common.ErrorMessages;
import vibes.exception.InvalidArgumentException;

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

    public static String parseTodo(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(SPACE_CHAR, SPLIT_TO_TWO);
        if (parts.length == SPLIT_TO_TWO_LENGTH) {
            return parts[TODO_DESC_INDEX];
        } else {
            throw new InvalidArgumentException(ErrorMessages.TODO_ARG_EMPTY);
        }
    }

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

    public static int parseTaskNumber(String userInput) throws InvalidArgumentException{
        String[] parts = userInput.split(SPACE_CHAR, SPLIT_TO_TWO);
        if (parts.length == SPLIT_TO_TWO_LENGTH){
            return Integer.parseInt(parts[TASK_NUMBER_INDEX]) - TASK_NUMBER_OFFSET;
        } else {
            throw new InvalidArgumentException(ErrorMessages.TASK_NUMBER_NOT_FOUND);
        }
    }

    public static String parseCommand(String userInput) {
        return userInput.split(SPACE_CHAR)[COMMAND_INDEX].toLowerCase();
    }
}
