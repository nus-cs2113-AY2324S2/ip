package exceptions;

/**
 * Represents an exception thrown when creating a blank task.
 */
public class EmptyTaskException extends Exception{
    /**
     * Retrieves the error message for this exception.
     * @return The error message indicating that the task is empty
     */
    public String getMessage(){
        return "Task cannot be empty!";
    }
}

