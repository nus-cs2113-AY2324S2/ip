package kvothe.exception;

/**
 * Represents an exception of the application. Is the most generic exception of the application.
 */
public class KvotheException extends Exception{
    public KvotheException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "OOPS!!! " + super.getMessage();
    }
}
