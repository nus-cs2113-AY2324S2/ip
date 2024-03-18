package chatman.exceptions;

/**
 * Implements custom exception class instantiated when command is entered with incorrect number of arguments.
 *
 * @author LWachtel1
 * */
public class IncorrectArgumentNumException extends ChatManException{

    /**
     * Constructor for IncorrectArgumentNumException class.
     **/
    public IncorrectArgumentNumException() {

    }

    /**
     * Prints error message notifying user they have entered a command with incorrect number of arguments then prompts
     * valid re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("Command has the incorrect number of arguments." +
                "\nPlease re-enter with expected number of arguments for its type");
    }
}
