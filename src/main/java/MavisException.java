/**
 * Represents custom exceptions that may occur during the execution of Mavis.
 */
public class MavisException extends Exception {

    /**
     * Handles exceptions that occur during the execution of Mavis commands.
     *
     * @param exception The exception that occurred.
     * @param input The user input at the time of the exception.
     */
    public static void handleException(Exception exception, String input) {
        final String SEPARATOR = "=====================================================================================================================";

        String[] splitInput = input.split(" ");

        System.out.println(SEPARATOR);

        if (exception instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("You are attempting to access a task that does not" +
                    " yet exist in the fabric of time");
        } else if (exception instanceof StringIndexOutOfBoundsException) {
            System.out.println("Incomplete " + splitInput[0] + " detected. " +
                    "Time is immutable, yet your description is incomplete. " +
                    "Rectify this anomaly immediately.");
        } else if (input.toLowerCase().startsWith("mark") || input.toLowerCase().startsWith("unmark")) {
            System.out.println("You are attempting to " + splitInput[0]  + ". " +
                    "To mark or unmark without a target is akin to swinging a blade in the dark.");
        } else if (exception instanceof IllegalArgumentException) {
            System.out.println("An unfamiliar command disrupts the flow of time, " +
                    "creating a discordant echo in the chamber.");
            Ui.listOptions();
        } else {
            System.out.println("Unknown Anomaly detected. The fabric of reality is in flux. " +
                    "Investigate and rectify this anomaly immediately.");
        }

        System.out.println(SEPARATOR);
    }
}
