package RuntimeSupport;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * The <code>DukeException</code> class provides a mechanism for handling exceptions
 * specific to the Duke application.
 * This class captures a variety of common errors that may occur during the execution
 * of the application and provides user-friendly error messages to aid in diagnosing
 * and resolving issues.
 */
public class DukeException extends Throwable {
    static String BREAK_LINE = "____________________________________________________________";

    /**
     * Processes and displays an appropriate message for the encountered exception.
     * This method identifies the type of exception and the context in which it was thrown,
     * displaying a specific message that guides the user towards resolving the problem.
     * It supports handling exceptions related to null pointer access, string index out of
     * bounds, array index out of bounds, I/O errors, and date-time parsing errors.
     *
     * @param error The exception that was caught during the application's execution.
     * @param line The command or input that triggered the exception, used to provide
     * context-specific feedback to the user.
     */
    public static void handleException(Exception error, String line) {
        if (error instanceof NullPointerException) {
            System.out.println("Alert! Galactic navigation systems report a phantom destination.");
            System.out.println("The operation: " + line + " cannot be done because the event does " +
                    "not exist!");
        } else if (error instanceof StringIndexOutOfBoundsException) {
            System.out.println("Uh-oh! Our canvas is blank!");
            System.out.println("The description of the " + line + " event cannot be empty!");
        } else if (error instanceof ArrayIndexOutOfBoundsException && line.contains(("delete"))) {
            System.out.println("Uh-Oh! There is nothing to delete here! " +
                    "Please specify a valid task number to delete!");
        } else if (error instanceof ArrayIndexOutOfBoundsException && !line.contains("mark")) {
            System.out.println("Echo... echo... Your event's lost in the echo chamber.");
            System.out.println("The event input: " + line + " cannot be recognized by the bot! :(");
            System.out.println("Please input the event details using the following format:");
            System.out.println("todo [todo event name].");
            System.out.println("deadline [deadline event name] /by [yyyy-MM-dd HHmm].");
            System.out.println("event [event name] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm].");
        } else if (error instanceof IOException) {
            System.out.println("Uh-Oh! An error occurred while saving or loading tasks: " + line);
        } else if (error instanceof DateTimeParseException) {
            System.out.println("I'm sorry but that is an invalid time format!");
            System.out.println("Please use the following time format: yyyy-MM-dd HHmm");
            System.out.println("Example: 2023-12-25 1800");
        } else if (line.contains("mark")){
            System.out.println("Oops! The task does not exist. Maybe you got the wrong number. :(");
        } else {
            System.out.println("I'm sorry, but I don't know what that means :-()");
        }
        System.out.println(BREAK_LINE);
    }
}
