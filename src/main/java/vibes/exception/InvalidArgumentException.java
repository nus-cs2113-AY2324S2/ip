package vibes.exception;

/**
 * Signals that an invalid argument was provided.
 */
public class InvalidArgumentException extends Exception {
    /**
     * @param message should contain relevant information on the failed argument(s)
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
