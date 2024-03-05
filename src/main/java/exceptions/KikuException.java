package exceptions;

public class KikuException extends Exception {
    /**
     * Custom exception class for KikuBot used to represent various errors that can occur during execution,
     * including command parsing, task manipulation and storage operations.
     *
     * @param message A custom error message that explains the reason for the exception.
     */
    public KikuException(String message) {
        super(message);
    }
}
