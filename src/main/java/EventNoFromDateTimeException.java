public class EventNoFromDateTimeException extends Exception {
    public EventNoFromDateTimeException() {
        super("ERROR: 'Event'-type task is missing starting date/time!");
    }
}
