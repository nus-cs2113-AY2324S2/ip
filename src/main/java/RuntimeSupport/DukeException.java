package RuntimeSupport;
import java.io.IOException;

public class DukeException {

    static String BREAK_LINE = "____________________________________________________________";
    public static void handleException(Exception error, String line) {

        if (error instanceof NullPointerException) {

            System.out.println("Alert! Galactic navigation systems report a phantom destination \uD83C\uDF0C. The operation: " + line + " cannot be done because the event does not exist!");

        } else if (error instanceof StringIndexOutOfBoundsException) {

            System.out.println("Uh-oh! Our canvas is blank. \uD83C\uDFA8 The description of the " + line + " event cannot be empty!");

        } else if (error instanceof ArrayIndexOutOfBoundsException && line.contains(("delete"))) {

            System.out.println("Uh-Oh! There is nothing to delete here! Please specify a valid task number to delete!");

        } else if (error instanceof ArrayIndexOutOfBoundsException) {

            System.out.println("Echo... echo... Your event's lost in the echo chamber. \uD83C\uDF0C The event input: " + line + " cannot be recognized by the bot! :(" + "\n");
            System.out.println("Please input the event details using the following format:");
            System.out.println("todo [todo event name].");
            System.out.println("deadline [deadline event name] /by [time].");
            System.out.println("event [event name] /from [start time] /to [end time].");

        } else if (error instanceof IOException) {

            System.out.println("Uh-Oh! An error occurred while saving or loading tasks: " + line);
        } else {

<<<<<<< HEAD
            System.out.println("Oops! I've no clue what that means, or the task does not exist. Could you enlighten me, please? \uD83E\uDD16\uD83D\uDCA1");

=======
            System.out.println("Oops! It seems my circuits are tangled. I've no clue what that means. Could you enlighten me, please? \uD83E\uDD16\uD83D\uDCA1");
>>>>>>> branch-Level-7
        }

        System.out.println(BREAK_LINE);
    }
}
