package Exception;

/**
 * Represents an exception for missing descriptions in user commands.
 * This exception is thrown when a command that requires additional details is entered without those details.
 */
public class MissingDescriptionException extends Exception{
    public MissingDescriptionException(String message) {
        super(message);
    }
}
