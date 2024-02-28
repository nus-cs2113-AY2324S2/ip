package Exceptions;

/**
 * The ThawException class represents a custom exception used in the application.
 * It extends the Exception class and allows for the creation of exceptions with a specific message.
 */
public class ThawException extends Exception {
    /**
     * Constructs a ThawException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public ThawException(String message) {
        super(message);
    }
}
