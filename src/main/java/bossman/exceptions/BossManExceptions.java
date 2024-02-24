package bossman.exceptions;

/**
 * BossManExceptions is the base class for exceptions specific to the BossMan application.
 * It extends the Exception class to handle custom exceptions.
 */
public class BossManExceptions extends Exception {

    /**
     * Constructs a BossManExceptions with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public BossManExceptions(String message) {
        super(message);
    }
}
