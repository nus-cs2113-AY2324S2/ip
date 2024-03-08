import java.util.Scanner;

/**
 * Provides a user interface for interacting with the program.
 */
public class Ui {
    /**
     * Scanner object for user input.
     */
    private Scanner in;

    /**
     * Constructs a new Ui object with a Scanner for input.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message indicating failure to load tasks from a file.
     */
    public void showLoadingError() {
        System.err.println("Error loading tasks from file.");
    }

    /**
     * Prints a welcome message to the console.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Brennan!");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints a line of dashes to the console for visual separation.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}