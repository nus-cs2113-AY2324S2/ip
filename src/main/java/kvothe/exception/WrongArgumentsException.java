package kvothe.exception;

/**
 * Exception for when the arguments passed in an input line are incorrect.
 */
public class WrongArgumentsException extends KvotheException {
    public WrongArgumentsException(String message){
        super(message);
    }

}
