package geepee.exceptions;

import geepee.system.SystemMessage;

public class MissingToException extends Exception {
    
    public static final String NEWLINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    public static final String EVENT_TEMPLATE = "\"event {description} /from {from} /to {to}\"";
    public static final String MESSAGE = "    The end (to) of the event is missing! The correct input is " +
            EVENT_TEMPLATE;

    public MissingToException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
