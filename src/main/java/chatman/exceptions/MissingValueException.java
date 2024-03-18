package chatman.exceptions;

/**
 * Implements custom exception class instantiated when user enters command missing arguments.
 *
 * @author LWachtel1
 * */
public class MissingValueException extends ChatManException {

    /**
     * Constructor for MissingValueException class.
     **/
    public MissingValueException() {

    }

    /**
     * Prints error message notifying user command entered is missing argument then prompts valid re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("Command is missing necessary accompanying details." +
                "\nPlease re-enter command and provide them");
    }
}
