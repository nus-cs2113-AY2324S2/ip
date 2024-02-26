package geepee.exceptions;

import geepee.system.SystemMessage;

public class InvalidCommandException extends Exception {

    /** Newline character */
    public static final String NEWLINE = System.lineSeparator();
    /** String representing a horizontal line */
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    /** Error message */
    public static final String MESSAGE = "    Invalid command! Valid commands are: todo, event, deadline, list";

    /**
     * Initialises an instance of the InvalidCommandException class.
     * Calls the Exception constructor to initialise the error message.
     */
    public InvalidCommandException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
