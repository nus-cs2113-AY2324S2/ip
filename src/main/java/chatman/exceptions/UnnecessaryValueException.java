package chatman.exceptions;

/**
 * Implements custom exception class instantiated when user provides arguments for command that does not require any.
 *
 * @author LWachtel1
 * */
public class UnnecessaryValueException extends ChatManException{

    /**
     * Constructor for UnnecessaryValueException class.
     **/
    public UnnecessaryValueException() {

    }

    /**
     * Prints error message notifying user that entered command does not require the provided arguments then prompts
     * valid re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("Command has unnecessary details provided." +
                "\nPlease re-enter command without them");
    }
}
