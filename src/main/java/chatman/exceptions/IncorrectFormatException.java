package chatman.exceptions;

/**
 * Implements custom exception class instantiated when user-entered command lacks required format for arguments.
 *
 * @author LWachtel1
 * */
public class IncorrectFormatException extends ChatManException{

    /**
     * Constructor for IncorrectFormatException class.
     **/
    public IncorrectFormatException() {

    }

    /**
     * Prints error message notifying user that command has been entered with incorrect formatting.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("Command entered had incorrect format.\nPlease re-enter with valid formatting.");
    }
}
