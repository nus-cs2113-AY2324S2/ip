package chatman.exceptions;

/**
 * Implements custom exception class instantiated when user enters mark/unmark command with non-numerical index or
 * out-of-bounds numerical index.
 *
 * @author LWachtel1
 * */
public class IncorrectMarkUnmarkException extends ChatManException {

    /**
     * Constructor for IncorrectMarkUnmarkException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public IncorrectMarkUnmarkException(String commandType, String erroneousInput) {
        super(commandType, erroneousInput);
    }

    /**
     * Prints error message notifying user index for mark/unmark command was not valid for use then prompts valid
     * re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.printf("mark/unmark Command has incorrect position value provided:'%s'."
                + "\nPlease re-enter with a valid numerical position within list bounds.\n",super.getErroneousInput());
    }
}
