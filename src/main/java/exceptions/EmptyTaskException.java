package exceptions;
import utils.Constants;

// Custom exception for handling invalid task input
public class EmptyTaskException extends Exception {
    public EmptyTaskException() {
        System.out.println(Constants.BREAKLINE);
        System.out.println("Baka, you have not entered the task!!!");
        System.out.println(Constants.BREAKLINE);
    }
}
