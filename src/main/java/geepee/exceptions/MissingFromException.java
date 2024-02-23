package geepee.exceptions;

import geepee.system.SystemMessage;

public class MissingFromException extends Exception {

    public static final String NEWLINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = SystemMessage.getHorizontalLine();
    public static final String EVENT_TEMPLATE = "\"event {description} /from {from} /to {to}\"";
    public static final String MESSAGE = "    The start (from) of the event is missing! The correct input is " +
            EVENT_TEMPLATE;

    public MissingFromException() {
        super(HORIZONTAL_LINE + NEWLINE + MESSAGE + NEWLINE + HORIZONTAL_LINE);
    }
}
