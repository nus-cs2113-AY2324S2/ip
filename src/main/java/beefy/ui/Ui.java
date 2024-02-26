package beefy.ui;

/**
 * Represents the user interface for Beefy chatbot.
 * Responsible for printing out messages on the command line interface.
 */
public class Ui {
    private static final String BOT_NAME = "BEEFY\n";
    private static final String SEPARATION = "____________________________________________________________________\n";

    /**
     * Edits the given message into a default message format.
     *
     * @param message The message to be displayed.
     * @return Formatted message.
     */
    private static String messageFormat(String message) {
        return SEPARATION + BOT_NAME + message + System.lineSeparator() + SEPARATION;
    }

    /**
     * Displays the exit message of Beefy chatbot.
     */
    public static void printHi() {
        System.out.print(messageFormat("Hello there, I'm Beefy, what can I do for you?"));
    }

    /**
     * Displays the message to prompt user to key in an input to Beefy chatbot.
     */
    public static void printUser() {
        System.out.println("You:");
    }

    /**
     * Displays the exit message of Beefy chatbot.
     */
    public static void printBye() {
        System.out.print(messageFormat("Good Bye, Hope to see you again!"));
    }

    /**
     * Displays message of command executed by Beefy chatbot.
     *
     * @param message Words to display.
     */
    public static void printMessage(String message) {
        System.out.print(messageFormat(message));
    }
}
