package exceptions;
public class EventNoToDateTimeException extends Exception {
    public EventNoToDateTimeException() {
        super("ERROR: 'Event'-type task is missing ending date/time!");
    }
}
