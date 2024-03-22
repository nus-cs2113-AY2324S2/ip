package chatman.exceptions;

import chatman.utility.Ui;

/**
 * Implements custom exception class instantiated when user enters mark/unmark or delete command with non-numerical
 * index or out-of-bounds numerical index.
 *
 * @author LWachtel1
 * */
public class IncorrectIndexException extends ChatManException {

    /**
     * Constructor for IncorrectIndexException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public IncorrectIndexException(String commandType, String erroneousInput) {
        super(commandType, erroneousInput);
    }

    /**
     * Prints error message notifying user index for mark/unmark or delete command was not valid for use then prompts
     * valid re-entry.
     * */
    @Override
    public void sendErrorMsg() {

        System.out.printf("%s%n%n", Ui.getChatbotSeparator());

        switch (super.getCommandType()) {
        case "DELETE":
            System.out.printf("Command 'delete' has incorrect position value provided:'%s'."
                    + "\nPlease re-enter with a valid numerical position within list bounds.\n",
                    super.getErroneousInput());
            break;

        case "MARKUNMARK":
            System.out.printf("Command 'mark/unmark' has incorrect position value provided:'%s'."
                            + "\nPlease re-enter with a valid numerical position within list bounds.\n",
                    super.getErroneousInput());
            break;
        }

    }
}
