import java.io.FileNotFoundException;
import java.io.IOException;

public class KratosException extends Exception {
    public static void handleException(Exception e, String command) {
        if (e instanceof IllegalArgumentException) {
            commandNotFound();
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            blankAfterTask(command);
        } else if (e instanceof IndexOutOfBoundsException) {
            taskOutOfBounds();
        } else if (e instanceof IOException) {
            System.out.println(command);
        } else if (e instanceof FileNotFoundException) {
            System.out.println(command);
        } else {
            otherIncurredException();
        }
    }

    private static void otherIncurredException() {
        System.out.println(Ui.LINE);
        System.out.println("An unexpected error has materialized, " +
                "shrouded in the veils of chaos. " +
                "We must navigate through this uncertainty with vigilance and resolve.");
        System.out.println(Ui.LINE);
    }

    private static void blankAfterTask(String command) {
        String errorMessage;
        switch (command.split(" ")[0]) {
        case "todo":
            errorMessage = Ui.taskMissing;
            break;
        case "deadline":
            errorMessage = Ui.deadlineMissing;
            break;
        case "event":
            errorMessage = Ui.eventMissing;
            break;
        default:
            errorMessage = Ui.taskMissing;
            break;
        }
        System.out.println(Ui.LINE);
        System.out.println(errorMessage);
        System.out.println(Ui.LINE);
    }

    private static void commandNotFound() {
        System.out.println(Ui.LINE);
        System.out.println("Verily, the COMMAND hath eluded us, " +
                "obscured in the realm of the unknown.\n" +
                "Let us seek clarity and purpose in our journey forward.");
        System.out.println(Ui.LINE);
    }

    private static void taskOutOfBounds() {
        System.out.println(Ui.LINE);
        System.out.println("The task you seek to vanquish lies beyond the reach of mortal hands.\n" +
                "Specify a valid task number or retreat from the brink of chaos.");
        System.out.println(Ui.LINE);
    }
}
