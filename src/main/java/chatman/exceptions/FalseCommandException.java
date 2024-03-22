package chatman.exceptions;

/**
 * Implements custom exception class instantiated when unrecognised command is entered by user.
 *
 * @author LWachtel1
 * */
public class FalseCommandException extends ChatManException{

    /**
     * Constructor for FalseCommandException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public FalseCommandException(String commandType, String erroneousInput) {
        super(commandType, erroneousInput);
    }

    /**
     * Prints error message notifying user they have entered an unrecognised command then prompts
     * valid re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.printf("Unrecognised command entered '%s' of command type '%s'."
                + "\nPlease re-enter a recognised command.\n", super.getErroneousInput(), super.getCommandType());
    }
}
