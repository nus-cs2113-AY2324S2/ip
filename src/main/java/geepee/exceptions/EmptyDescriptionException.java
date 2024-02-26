package geepee.exceptions;

import geepee.system.SystemMessage;

public class EmptyDescriptionException extends Exception {

    public static final String NEWLINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    public static final String TODO_TEMPLATE = "\"todo {description}\"";
    public static final String DEADLINE_TEMPLATE = "\"deadline {description} /by {deadline}\"";
    public static final String EVENT_TEMPLATE = "\"event {description} /from {from} /to {to}\"";
    public static final String KEYWORD_TEMPLATE = "\"find {keyword}\"";

    public String getEmptyTodoMessage() {
        String message = "    The description of a todo cannot be empty! The correct input is " +
                TODO_TEMPLATE;
        return HORIZONTAL_LINE + NEWLINE + message + NEWLINE + HORIZONTAL_LINE;
    }

    public String getEmptyDeadlineMessage() {
        String message = "    The description of a deadline cannot be empty! The correct input is " +
                DEADLINE_TEMPLATE;
        return HORIZONTAL_LINE + NEWLINE + message + NEWLINE + HORIZONTAL_LINE;
    }

    public String getEmptyEventMessage() {
        String message = "    The description of an event cannot be empty! The correct input is " +
                EVENT_TEMPLATE;
        return HORIZONTAL_LINE + NEWLINE + message + NEWLINE + HORIZONTAL_LINE;
    }

    public String getEmptyKeywordMessage() {
        String message = "    The keyword to look for is empty! The correct input is " +
                KEYWORD_TEMPLATE;
        return HORIZONTAL_LINE + NEWLINE + message + NEWLINE + HORIZONTAL_LINE;
    }
}
