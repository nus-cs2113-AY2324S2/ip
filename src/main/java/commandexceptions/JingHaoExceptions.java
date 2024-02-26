package commandexceptions;

/**
 * JingHaoExceptions is the base class for exceptions specific to the BossMan application.
 * It extends the Exception class to handle custom exceptions.
 */
public class JingHaoExceptions extends Exception {
    /**
     * Constructs a JingHaoExceptions with the specified error message.
     *
     * @param message The error message of the exception.
     */
    public JingHaoExceptions(String message) {
        super(message);
    }
}
