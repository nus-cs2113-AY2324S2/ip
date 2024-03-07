package exceptions;
/**
 * Represents an exception thrown when creating an event with no start date or time.
 */
public class MissingStartException extends Exception{

    /**
     * Retrieves the error message for this exception.
     * @return The error message indicating that the start date/time is missing
     */
    public String getMessage(){
        return "Start Date is missing. Include when this event starts as such:" +
                "{event} /from {start date} /to {end date}";
    }
}
