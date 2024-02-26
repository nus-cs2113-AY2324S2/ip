package geepee.exceptions;

import geepee.system.SystemMessage;

public class MissingFromException extends Exception {

    /** Newline character */
    public static final String NEWLINE = System.lineSeparator();
    /** String representing a horizontal line */
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    /** Template of correct event input */
    public static final String EVENT_TEMPLATE = "\"event {description} /from {from} /to {to}\"";
    /** Error message */
    public static final String MESSAGE = "    The start (from) of the event is missing! The correct input is " +
            EVENT_TEMPLATE;

    /**
     * Initialises an instance of the MissingFromException class.
     * Calls the Exception constructor to initialise the error message.
     */
    public MissingFromException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
