package geepee.exceptions;

import geepee.system.SystemMessage;

public class MissingDeadlineException extends Exception {
    
    public static final String NEWLINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    public static final String DEADLINE_TEMPLATE = "\"deadline {description} /by {deadline}\"";
    public static final String MESSAGE = "    The deadline of the task is missing! The correct input is " +
            DEADLINE_TEMPLATE;

    public MissingDeadlineException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
