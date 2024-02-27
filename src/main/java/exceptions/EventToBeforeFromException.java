package exceptions;

/**
 * Exception thrown when the "Event"-date time is specified with end date/time preceding start date/time,
 * thus violating the format
 */
public class EventToBeforeFromException extends Exception {

    /**
     * Constructor for this exception with an error message indicating the formatting error of end date/time
     * preceding start date/time
     */
    public EventToBeforeFromException() {
        super("ERROR: 'Event'-type task has reversed start and end date/time!");
    }
}
