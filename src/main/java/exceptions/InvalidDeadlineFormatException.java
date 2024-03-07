package exceptions;

/**
 * The InvalidDeadlineFormatException class represents an exception that is thrown
 * when the deadline format provided by the user is invalid.
 */

public class InvalidDeadlineFormatException extends Exception {

    public InvalidDeadlineFormatException(String message) {
        super(message);
    }
}

