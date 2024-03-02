package RuntimeSupport;
import java.io.IOException;

public class DukeException extends Throwable {

    static String BREAK_LINE = "____________________________________________________________";
    public static void handleException(Exception error, String line) {

        if (error instanceof NullPointerException) {

            System.out.println("Alert! Galactic navigation systems report a phantom destination.");
            System.out.println("The operation: " + line + " cannot be done because the event does not exist!");

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
            System.out.println("deadline [deadline event name] /by [time].");
            System.out.println("event [event name] /from [start time] /to [end time].");

        } else if (error instanceof IOException) {

            System.out.println("Uh-Oh! An error occurred while saving or loading tasks: " + line);
        } else if (line.contains("mark")){

            System.out.println("Oops! The task does not exist. Maybe you got the wrong number. :(");
        } else {

            System.out.println("I'm sorry, but I don't know what that means :-()");
        }

        System.out.println(BREAK_LINE);
    }
}
