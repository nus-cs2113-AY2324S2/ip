package exceptions;

/**
 * Exception thrown when a task to be added has no name specified
 */
public class TaskNoNameException extends Exception {

    /**
     * Constructor for this exception with an error message indicating the lack of name
     */
    public TaskNoNameException() {
        super("ERROR: Task not given a name!");
    }
}
