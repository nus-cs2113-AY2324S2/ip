package Exceptions;

/**
 * The InvalidEventFormatException class represents an exception that is thrown
 * when the event format provided by the user is invalid.
 */

public class InvalidEventFormatException extends Exception {

    /**
     * Constructs a new InvalidEventFormatException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */

    public InvalidEventFormatException(String message) {
        super(message);
    }
}

