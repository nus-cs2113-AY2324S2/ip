package bob.exceptions;

public class InvalidTaskNumberException extends Exception {
    private final String command;
    public static final String ERROR_MESSAGE = " Please provide a valid task number!\n\n";

    public InvalidTaskNumberException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        String errorMessage = ERROR_MESSAGE + " Usage:\n";

        if (command.equals("MARK")) {
            errorMessage += "  mark <task number>";
        } else if (command.equals("UNMARK")){
            errorMessage += "  unmark <task number>";
        } else {
            errorMessage += "  delete <task number>";
        }

        return errorMessage;
    }
}
