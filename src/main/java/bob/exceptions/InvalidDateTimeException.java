package bob.exceptions;

/**
 * Bob Exception for invalid date/times provided by the User.
 */
public class InvalidDateTimeException extends Exception {
    public static final String INVALID_END_TIME = " Please enter a valid end date/time!\n\n";
    public static final String START_AFTER_END = " Please ensure that the Event does not start after its end date!\n\n";
    private final String command;
    private final String errorType;

    public InvalidDateTimeException(String command, String errorType) {
        this.command = command;
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        String errorMessage;

        if (errorType.equals(INVALID_END_TIME)) {
            errorMessage = INVALID_END_TIME + " Usage:\n";
        } else {
            errorMessage = START_AFTER_END + " Usage:\n";
        }

        if (command.equals("DEADLINE")) {
            errorMessage += "  deadline <task name> /by <due date> (date format: dd/mm/yyyy hh:mm)";
        } else {
            errorMessage += "  event <task name> /from <start date> /to <end date> (date format: dd/mm/yyyy hh:mm)";
        }

        return errorMessage;
    }
}
