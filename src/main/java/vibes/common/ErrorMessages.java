package vibes.common;

/**
 * Container for error messages used in the application.
 */
public class ErrorMessages {
    public static final String INVALID_COMMAND = "\t Invalid Command. Please choose between: todo, " + "deadline, " +
            "event, mark, unmark, delete, and bye";
    public static final String FILE_NOT_FOUND = "File not Found!";
    public static final String FAILED_TO_CREATE_FOLDER = "Failed to create data folder.";
    public static final String FAILED_TO_CREATE_FILE = "Failed to create data file.";
    public static final String ERROR_OCCURRED = "An error occurred: ";
    public static final String TODO_ARG_EMPTY = "\t Argument not found! The todo task cannot be empty.";
    public static final String BY_DATE_NOT_FOUND = "\t Invalid input format. '/by' separator not found.";
    public static final String DEADLINE_ARG_EMPTY = "\t Argument not found! The deadline task cannot be empty";
    public static final String FROM_OR_TO_DATE_NOT_FOUND = "\t Invalid input format. '/from' or '/to' separator not " +
            "found.";
    public static final String EVENT_ARG_EMPTY = "\t Argument not found! The event task cannot be empty";
    public static final String TASK_NUMBER_NOT_FOUND = "\t Argument not found! Task number is required";
    public static final String KEYWORD_NOT_FOUND = "\t Argument not found! Keyword is required";
}
