package exceptions;

public class EmptyTaskException extends Exception{
    public String getMessage(){
        return "Event cannot be empty!";
    }
}

