package helperFunctions;

public class InvalidParamsException extends Exception{
    public InvalidParamsException(String errorMessage) { // constructor
        super(errorMessage);
    }

}
