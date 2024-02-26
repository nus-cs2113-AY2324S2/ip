package natsu.exception;

/**
 * Custom exception class that represents an error condition when a command
 * given to the application is invalid. This exception is thrown to indicate
 * various error states such as invalid task numbers, missing command parameters,
 * or unrecognized commands.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs an {@code InvalidCommandException} with the specified detail message.
     * The message provides more information about the error, such as what was invalid
     * about the command and potential ways to correct it.
     *
     * @param message The detail message explaining the reason this exception is thrown.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
