package bob.exceptions;

import bob.utils.Command;

public class InvalidTaskNumberException extends Exception {
    private final Command command;
    public static final String ERROR_MESSAGE = " Please provide a valid task number!\n\n";

    public InvalidTaskNumberException(Command command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        String errorMessage = ERROR_MESSAGE + " Usage:\n";

        if (command.equals(Command.MARK)) {
            errorMessage += "  mark <task number>";
        } else if (command.equals(Command.UNMARK)){
            errorMessage += "  unmark <task number>";
        } else {
            errorMessage += "  delete <task number>";
        }

        return errorMessage;
    }
}
