package exceptions;

public class MissingDeadlineException extends Exception{

    public String getMessage(){
        return "Include when this task ends.";
    }
}

