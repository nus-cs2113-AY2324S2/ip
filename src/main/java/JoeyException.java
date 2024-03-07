/**
 * The JoeyException class is a custom exception that extends the built-in Exception class.
 * It is used to handle errors in the application by providing a specific exception type for
 * situations related to Joey, the character mentioned in the error messages.
 */
public class JoeyException extends Exception {
    //Use exceptions to handle errors
    /**
     * Constructs a JoeyException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public JoeyException(String message) {
        super(message);
    }
}