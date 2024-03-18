package chatman.exceptions;

/**
 * Implements custom exception class instantiated when user enters mark/unmark command with non-numerical index or
 * out-of-bounds numerical index.
 *
 * @author LWachtel1
 * */
public class IncorrectMarkUnmarkException extends ChatManException{

    /**
     * Constructor for IncorrectMarkUnmarkException class.
     **/
    public IncorrectMarkUnmarkException() {

    }

    /**
     * Prints error message notifying user index for mark/unmark command was not valid for use then prompts valid
     * re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("Mark/Unmark Command has incorrect position value provided." +
                "\nPlease re-enter with a valid list position.");
    }
}
