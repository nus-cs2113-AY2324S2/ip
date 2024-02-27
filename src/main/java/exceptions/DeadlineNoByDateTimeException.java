package exceptions;

/**
 * Exception thrown when a Deadline-type task has no due date/time specified
 */
public class DeadlineNoByDateTimeException extends Exception {

    /**
     * Constructor for this exception with an error message indicating the lack of due date/time
     */
    public DeadlineNoByDateTimeException() {
        super("ERROR: 'Deadline'-type task is missing due date!");
    }
}
