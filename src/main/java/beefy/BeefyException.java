package beefy;

/**
 * Represents an exception for Beefy chatbot.
 */
public class BeefyException extends Exception {
    /**
     * Constructs a new BeefyException object with the error message.
     *
     * @param message The error message of the exception.
     */
    public BeefyException(String message) {
        super(message);
    }
}
