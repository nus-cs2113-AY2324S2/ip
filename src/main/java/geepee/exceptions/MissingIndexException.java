package geepee.exceptions;

import geepee.system.SystemMessage;

public class MissingIndexException extends Exception {

    /** Newline character */
    public static final String NEWLINE = System.lineSeparator();
    /** String representing a horizontal line */
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    /** Error message */
    public static final String MESSAGE = "    No index provided!";

    /**
     * Initialises an instance of the MissingIndexException class.
     * Calls the Exception constructor to initialise the error message.
     */
    public MissingIndexException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
