package cody;

/**
 * The CodyException class is a custom exception class used to handle exceptions specific to the Cody application.
 */
public class CodyException extends Exception {

    /**
     * Constructs a new CodyException with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public CodyException(String message) {
        super(message);
    }
}
