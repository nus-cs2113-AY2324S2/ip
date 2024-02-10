public class EventToBeforeFromException extends Exception {
    public EventToBeforeFromException() {
        super("ERROR: 'Event'-type task has reversed start and end date/time!");
    }
}
