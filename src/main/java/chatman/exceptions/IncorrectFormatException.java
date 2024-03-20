package chatman.exceptions;

/**
 * Implements custom exception class instantiated when user-entered command lacks required format for arguments.
 *
 * @author LWachtel1
 * */
public class IncorrectFormatException extends ChatManException{

    /**
     * Constructor for IncorrectFormatException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public IncorrectFormatException(String commandType, String erroneousInput) {
        super(commandType, erroneousInput);
    }

    /**
     * Prints error message notifying user that command has been entered with incorrect formatting.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");

        switch (super.getCommandType()) {
        case "TODO":
            System.out.printf("Command 'todo' entered with incorrect formatting:'%s'" +
                            "\nPlease re-enter with valid formatting (no blank arguments)" +
                            "\nFormat: 'deadline DESC_ARG'\n",
                    super.getErroneousInput());
            break;

        case "DEADLINE":
            System.out.printf("Command 'deadline' entered with incorrect formatting:'%s'" +
                            "\nPlease re-enter with valid formatting (no blank arguments)" +
                            "\nFormat: 'deadline DESC_ARG /by DEADLINE_ARG'\n",
                    super.getErroneousInput());
            break;

        case "EVENT":
            System.out.printf("Command 'event' entered with incorrect formatting:'%s'" +
                            "\nPlease re-enter with valid formatting (no blank arguments)" +
                            "\nFormat: 'event DESC_ARG /from START_ARG /to END_ARG'\n",
                    super.getErroneousInput());
            break;

        default:
            break;
        }
    }
}
