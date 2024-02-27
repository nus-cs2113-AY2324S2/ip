package exceptions;

/**
 * Exception thrown when an "Event"-type task has no end date/time specified
 */
public class EventNoToDateTimeException extends Exception {

    /**
     * Constructor for this exception with an error message indicating the lack of end date/time
     */
    public EventNoToDateTimeException() {
        super("ERROR: 'Event'-type task is missing ending date/time!");
    }
}
