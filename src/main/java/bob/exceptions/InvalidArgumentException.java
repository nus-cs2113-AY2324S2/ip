package bob.exceptions;

/**
 * Bob Exception for invalid arguments provided by the User.
 */
public class InvalidArgumentException extends Exception {
    private static final String ERROR_MESSAGE = " Please ensure your command is properly formatted!\n\n";
    private final String command;

    public InvalidArgumentException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        String errorMessage = ERROR_MESSAGE + " Usage:\n";

        switch (this.command) {
        case "DELETE":
            errorMessage += "  delete <task number>";
            break;
        case "MARK":
            errorMessage += "  mark <task number>";
            break;
        case "UNMARK":
            errorMessage += "  unmark <task number>";
            break;
        case "TODO":
            errorMessage += "  todo <task name>";
            break;
        case "DEADLINE":
            errorMessage += "  deadline <task name> /by <due date> (date format: dd/mm/yyyy hh:mm)";
            break;
        case "EVENT":
            errorMessage += "  event <task name> /from <start date> /to <end date> (date format: dd/mm/yyyy hh:mm)";
            break;
        }

        return errorMessage;
    }
}
