public class DukeException {

    public static void handleException(Exception error, String line) {

        if (error instanceof NullPointerException) {

            System.out.println("Alert! Galactic navigation systems report a phantom destination \uD83C\uDF0C. The operation: " + line + " cannot be done because the event does not exist!");

        } else if (error instanceof StringIndexOutOfBoundsException) {

            System.out.println("Uh-oh! Our canvas is blank. \uD83C\uDFA8 The description of the " + line + " event cannot be empty!");

        } else if (error instanceof ArrayIndexOutOfBoundsException && line.contains("mark")) {

            System.out.println("Whoops! We've hit a ghost event. It's simply not there. \uD83D\uDC7B The operation: " + line + " cannot be done because the event does not exist!");

        } else if (error instanceof ArrayIndexOutOfBoundsException) {

            System.out.println("Echo... echo... Your event's lost in the echo chamber. \uD83C\uDF0C The event input: " + line + " cannot be recognized by the bot! :(" + "\n");
            System.out.println("Please input the event details using the following format:");
            System.out.println("todo [todo event name].");
            System.out.println("deadline [deadline event name] /by [time].");
            System.out.println("event [event name] /from [start time] /to [end time].");

        } else {

            System.out.println("Oops! It seems my circuits are tangled. I've no clue what that means. Could you enlighten me, please? \uD83E\uDD16\uD83D\uDCA1");

        }

        System.out.println(Duke.BREAK_LINE);
    }
}
