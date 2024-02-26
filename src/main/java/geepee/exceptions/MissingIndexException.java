package geepee.exceptions;

import geepee.system.SystemMessage;

public class MissingIndexException extends Exception {

    public static final String NEWLINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    public static final String MESSAGE = "    No index provided!";

    public MissingIndexException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
