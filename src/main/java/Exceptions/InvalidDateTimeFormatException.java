package Exceptions;
/**
 * The InvalidDateTimeFormatException class represents an exception that is thrown
 * when the Date or time format provided by the user is invalid.
 */
public class InvalidDateTimeFormatException extends Exception {

    /**
     * Constructs a new InvalidDateTimeFormatException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}
