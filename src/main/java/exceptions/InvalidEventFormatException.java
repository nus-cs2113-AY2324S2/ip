package exceptions;

/**
 * The InvalidEventFormatException class represents an exception that is thrown
 * when the event format provided by the user is invalid.
 */

public class InvalidEventFormatException extends Exception {

    public InvalidEventFormatException(String message) {
        super(message);
    }
}

