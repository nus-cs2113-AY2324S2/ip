package alpaca.exceptions;

/**
 * Represents an exception when the task description is empty
 */
public class EmptyTaskDescriptionException extends AlpacaException{
    private String message;
    public EmptyTaskDescriptionException(String message) {
        this.message = message;
    }
    @Override
    public String toString () {
        return getMessage();
    }
}
