package bob.exceptions;

/**
 * Bob Exception for invalid commands provided by the User.
 */
public class InvalidCommandException extends Exception {
    private static final String ERROR_MESSAGE = " I'm sorry, but I don't understand your command!\n\n";
    private static final String COMMAND_USAGE_MESSAGE = " Supported commands:\n" +
            "  list\n" +
            "  delete <task number>\n" +
            "  mark <task number>\n" +
            "  unmark <task number>\n" +
            "  todo <task name>\n" +
            "  deadline <task name> /by <due date> (date format: dd/mm/yyyy hh:mm)\n" +
            "  event <task name> /from <start date> /to <end date> (date format: dd/mm/yyyy hh:mm)\n" +
            "  bye";

    @Override
    public String getMessage() {
        return ERROR_MESSAGE + COMMAND_USAGE_MESSAGE;
    }
}
