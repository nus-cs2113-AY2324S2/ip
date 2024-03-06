package Exceptions;
/**
 * The InvalidDateTimeFormatException class represents an exception that is thrown
 * when the Date or time format provided by the user is invalid.
 */
public class InvalidDateTimeFormatException extends Exception {
    
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}
