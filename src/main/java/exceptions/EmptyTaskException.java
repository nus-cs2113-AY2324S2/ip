package exceptions;
import utils.constants;

// Custom exception for handling invalid task input
public class EmptyTaskException extends Exception {
    public EmptyTaskException() {
        System.out.println(constants.BREAKLINE);
        System.out.println("You have not entered the task!!!");
        System.out.println(constants.BREAKLINE);
    }
}
