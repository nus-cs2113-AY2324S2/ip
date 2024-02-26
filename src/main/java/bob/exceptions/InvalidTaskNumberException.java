package bob.exceptions;

/**
 * Bob Exception for invalid task numbers provided by the User.
 */
public class InvalidTaskNumberException extends Exception {
    public static final String ERROR_MESSAGE = " Please provide a valid task number!\n\n";
    private final String command;

    public InvalidTaskNumberException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        String errorMessage = ERROR_MESSAGE + " Usage:\n";

        if (command.equals("MARK")) {
            errorMessage += "  mark <task number>";
        } else if (command.equals("UNMARK")) {
            errorMessage += "  unmark <task number>";
        } else {
            errorMessage += "  delete <task number>";
        }

        return errorMessage;
    }
}
