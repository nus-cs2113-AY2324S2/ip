package exceptions;

public class MissingStartException extends Exception{

    public String getMessage(){
        return "Include when this event starts as such:" +
                "{event} /from {start date} /to {end date}";
    }
}
