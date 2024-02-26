package geepee.exceptions;

import geepee.system.SystemMessage;

public class MissingDeadlineException extends Exception {
    
    /** Newline character */
    public static final String NEWLINE = System.lineSeparator();
    /** String representing a horizontal line */
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    /** Template of correct deadline input */
    public static final String DEADLINE_TEMPLATE = "\"deadline {description} /by {deadline}\"";
    /** Error message */
    public static final String MESSAGE = "    The deadline of the task is missing! The correct input is " +
            DEADLINE_TEMPLATE;

    /**
     * Initialises an instance of the MissingDeadlineException class.
     * Calls the Exception constructor to initialise the error message.
     */
    public MissingDeadlineException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
