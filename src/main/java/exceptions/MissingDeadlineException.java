package exceptions;

/**
 * Represents an exception thrown when creating a task with no end date or deadline.
 */
public class MissingDeadlineException extends Exception{
    /**
     * Retrieves the error message for this exception.
     * @return The error message indicating that the end date/deadline is missing
     */
    public String getMessage(){
        return "Include when this task ends.";
    }
}

