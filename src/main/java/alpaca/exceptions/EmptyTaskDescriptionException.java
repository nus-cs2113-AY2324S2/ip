package alpaca.exceptions;

/**
 * Represents an exception when the task description is empty
 */
public class EmptyTaskDescriptionException extends Exception{
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }
}
