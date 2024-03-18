package chatman.exceptions;

/**
 * Implements custom exception class instantiated when unrecognised command is entered by user.
 *
 * @author LWachtel1
 * */
public class FalseCommandException extends ChatManException{

    /**
     * Constructor for FalseCommandException class.
     **/
    public FalseCommandException() {

    }

    /**
     * Prints error message notifying user they have entered an unrecognised command then prompts
     * valid re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("Unrecognised command entered.\nPlease re-enter a recognised command.");
    }
}
