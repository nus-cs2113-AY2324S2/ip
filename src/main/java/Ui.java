import java.util.Scanner;

/**
 * Handles all user interactions for the application. This class is responsible for
 * displaying messages to the user and reading user input from the command line.
 */
public class Ui {
    private final Scanner scanner; // Scanner object to read user input.

    /**
     * Constructs a Ui object, initializing the scanner to read input from the command line.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for a command and returns the trimmed input.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        System.out.println("\nEnter your command:");
        return scanner.nextLine().trim();
    }

    /**
     * Displays a welcome message to the user at the start of the application.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm TaskManager\nWhat can I do for you?");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a line of text to the user. Can be used for displaying
     * informational messages or command results.
     *
     * @param message The message to be displayed.
     */
    public void showLine(String message) {
        System.out.println(message);
    }

    /**
     * Displays a goodbye message to the user when exiting the application.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
