/**
 * Custom exception class for handling specific errors within the Phoebe application.
 * This class extends the standard Java Exception class, allowing for the creation of
 * application-specific error messages that can be caught and handled within the application.
 */
public class PhoebeException extends Exception {

    /**
     * Constructs a new PhoebeException with the specified detail message.
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     *
     * @param message the detail message. The detail message is saved for later retrieval
     *                by the Throwable.getMessage() method.
     */
    public PhoebeException(String message) {
        super(message);
    }
}

