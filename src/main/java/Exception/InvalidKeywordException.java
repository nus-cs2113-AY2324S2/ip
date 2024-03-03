package Exception;

/**
 * Represents an exception for invalid keywords entered by the user.
 * This exception is thrown when the user inputs a command that does not match any recognized commands.
 */
public class InvalidKeywordException extends Exception{
    public InvalidKeywordException(String message){
        super(message);
    }
}
