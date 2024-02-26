package exceptions;
import utils.constants;

// Custom exception for handling invalid task input
public class EmptyTaskException extends Exception {
    public EmptyTaskException() {
        super("The description of a task cannot be empty!!!");
    }
}
