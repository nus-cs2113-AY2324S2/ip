package bossman.ui;

/**
 * Ui class handles user interface related functionalities
 * such as displaying messages,prompting user input, and
 * printing separators.
 */
public class Ui {
    private static final String BOT_NAME = "BossMan";
    private static final String SEP = "____________________________________________________________";

    /**
     * Greets the user upon starting the application.
     * Displays a welcome message including the chatbot's name.
     */
    public static void greetUser() {
        System.out.println(SEP + "\nHello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?\n" + SEP);
    }

    /**
     * Prints the prompt for user input.
     */
    public static void promptUserInput() {
        System.out.print("You:");
    }

    /**
     * Says goodbye to the user upon exiting the application.
     * Displays a farewell message.
     */
    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n" + SEP);
    }

    /**
     * Prints a message followed by a separator and a new line.
     *
     * @param message the message to be printed
     */
    public static void printMessageWithSepNewLine(String message) {
        System.out.println(message + "\n" + SEP);
    }

    /**
     * Prints a message without a separator, on the same line.
     *
     * @param message the message to be printed
     */
    public static void printMessageNoSepSameLine(String message) {
        System.out.print(message);
    }

    /**
     * Prints a message without a separator, followed by a new line.
     *
     * @param message the message to be printed
     */
    public static void printMessageNoSepNewLine(String message) {
        System.out.println(message);
    }

    /**
     * Prints a new line followed by a separator.
     */
    public static void printNewLineWithSep(){
        System.out.println("\n" + SEP);
    }

    /**
     * Prints a separator.
     */
    public static void printSep() {
        System.out.println(SEP);
    }


}
