package exceptions;

/**
 * Exception thrown when an Event-type task has no start date/time specified
 */
public class EventNoFromDateTimeException extends Exception {

    /**
     * Constructor for this exception with an error message indicating the lack of start date/time
     */
    public EventNoFromDateTimeException() {
        super("ERROR: 'Event'-type task is missing starting date/time!");
    }
}
