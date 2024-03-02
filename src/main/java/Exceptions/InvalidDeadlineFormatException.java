package Exceptions;

/**
 * The InvalidDeadlineFormatException class represents an exception that is thrown
 * when the deadline format provided by the user is invalid.
 */

public class InvalidDeadlineFormatException extends Exception {

    /**
     * Constructs a new InvalidDeadlineFormatException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */

    public InvalidDeadlineFormatException(String message) {
        super(message);
    }
}

