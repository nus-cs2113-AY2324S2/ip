package bob;

/**
 * Represents an exception that is thrown when an error occurs in the program.
 */
public class BobException extends Exception {

    /**
     * Constructor for the BobException class.
     * @param message The error message.
     */
    public BobException(String message) {
        super("ERROR: " + message);
    }
}
