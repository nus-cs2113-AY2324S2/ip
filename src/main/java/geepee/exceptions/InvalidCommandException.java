package geepee.exceptions;

import geepee.system.SystemMessage;

public class InvalidCommandException extends Exception {

    public static final String NEWLINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    public static final String MESSAGE = "    Invalid command! Valid commands are: todo, event, deadline, list";

    public InvalidCommandException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
