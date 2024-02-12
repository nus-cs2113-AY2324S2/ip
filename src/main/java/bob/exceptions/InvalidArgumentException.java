package bob.exceptions;

import bob.utils.Command;

public class InvalidArgumentException extends Exception {
    private final Command command;
    private static final String ERROR_MESSAGE = " Please ensure your command is properly formatted!\n\n";

    public InvalidArgumentException(Command command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        String errorMessage = ERROR_MESSAGE + " Usage:\n";

        switch (this.command) {
        case MARK:
            errorMessage += "  mark <task number>";
            break;
        case UNMARK:
            errorMessage += "  unmark <task number>";
            break;
        case TODO:
            errorMessage += "  todo <task name>" ;
            break;
        case DEADLINE:
            errorMessage += "  deadline <task name> /by <due date>";
            break;
        case EVENT:
            errorMessage += "  event <task name> /from <start date> /to <end date>";
            break;
        }

        return errorMessage;
    }
}
