/**
 * Represents a custom exception class for handling errors specific to the application.
 * {@code HandleException} is used to encapsulate user-related errors, providing
 * more context and control over error handling within the application.
 */
public class HandleException extends Exception {

    /**
     * Constructs a new {@code HandleException} with the specified detail message.
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
     *
     * @param message the detail message. The detail message is saved for later retrieval
     *                by the {@link #getMessage()} method.
     */
    public HandleException(String message) {
        super(message);
    }
}


