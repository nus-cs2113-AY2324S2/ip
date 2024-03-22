package chatman.exceptions;

import chatman.utility.Ui;

/**
 * Implements custom exception class instantiated when command is entered with incorrect number of arguments.
 *
 * @author LWachtel1
 * */
public class IncorrectArgumentNumException extends ChatManException{

    /**
     * Constructor for IncorrectArgumentNumException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public IncorrectArgumentNumException(String commandType, String erroneousInput) {
        super(commandType, erroneousInput);
    }

    /**
     * Prints error message notifying user they have entered a command with incorrect number of arguments then prompts
     * valid re-entry.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", Ui.getChatbotSeparator());

        switch (super.getCommandType()) {
        case "BYE":
            System.out.printf("Command 'bye' entered with unnecessary arguments:'%s'"
                    + "\nPlease re-enter with 0 arguments "
                    + "\nFormat: 'bye'\n", super.getErroneousInput());
            break;

        case "LIST":
            System.out.printf("Command 'list' entered with unnecessary arguments:'%s'"
                    + "\nPlease re-enter with 0 arguments "
                    + "\nFormat: 'list'\n", super.getErroneousInput());
            break;

        case "TODO":
            System.out.printf("Command 'todo' entered with incorrect number of arguments:'%s'"
                            + "\nPlease re-enter with 1 argument"
                            + "\nFormat: 'deadline DESC_ARG'\n",
                    super.getErroneousInput());
            break;

        case "DEADLINE":
            System.out.printf("Command 'deadline' entered with incorrect number of arguments:'%s'"
                    + "\nPlease re-enter with 2 arguments separated by 1 /"
                    + "\nFormat: 'deadline DESC_ARG /by DEADLINE_ARG'\n",
                    super.getErroneousInput());
            break;

        case "EVENT":
            System.out.printf("Command 'event' entered with incorrect number of arguments:'%s'"
                            + "\nPlease re-enter with 3 arguments separated by 2 /"
                            + "\nFormat: 'event DESC_ARG /from START_ARG /to END_ARG'\n",
                    super.getErroneousInput());
            break;

        case "MARKUNMARK":
            System.out.printf("Command 'mark/unmark' entered with incorrect number of arguments:'%s'"
                            + "\nPlease re-enter with 1 argument"
                            + "\nFormat: 'mark POSITION_ARG' OR 'unmark POSITION_ARG'\n",
                    super.getErroneousInput());
            break;

        default:
            break;
        }
    }
}
