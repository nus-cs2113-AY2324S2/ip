package exceptions;
public class DeadlineNoByDateTimeException extends Exception {
    public DeadlineNoByDateTimeException() {
        super("ERROR: 'Deadline'-type task is missing due date!");
    }
}
