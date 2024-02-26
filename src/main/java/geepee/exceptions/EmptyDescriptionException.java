package geepee.exceptions;

import geepee.system.SystemMessage;

public class EmptyDescriptionException extends Exception {

    /** Newline character */
    public static final String NEWLINE = System.lineSeparator();
    /** String representing a horizontal line */
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    /** Template of correct todo input */
    public static final String TODO_TEMPLATE = "\"todo {description}\"";
    /** Template of correct deadline input */
    public static final String DEADLINE_TEMPLATE = "\"deadline {description} /by {deadline}\"";
    /** Template of correct event input */
    public static final String EVENT_TEMPLATE = "\"event {description} /from {from} /to {to}\"";

    /**
     * Returns a string of the error message when the user tries to initialise a todo task
     * with an empty description.
     */
    public String getEmptyTodoMessage() {
        String message = "    The description of a todo cannot be empty! The correct input is " +
                TODO_TEMPLATE;
        return HORIZONTAL_LINE + NEWLINE + message + NEWLINE + HORIZONTAL_LINE;
    }

    /**
     * Returns a string of the error message when the user tries to initialise a deadline task
     * with an empty description.
     */
    public String getEmptyDeadlineMessage() {
        String message = "    The description of a deadline cannot be empty! The correct input is " +
                DEADLINE_TEMPLATE;
        return HORIZONTAL_LINE + NEWLINE + message + NEWLINE + HORIZONTAL_LINE;
    }

    /**
     * Returns a string of the error message when the user tries to initialise an event task
     * with an empty description.
     */
    public String getEmptyEventMessage() {
        String message = "    The description of an event cannot be empty! The correct input is " +
                EVENT_TEMPLATE;
        return HORIZONTAL_LINE + NEWLINE + message + NEWLINE + HORIZONTAL_LINE;
    }
}
