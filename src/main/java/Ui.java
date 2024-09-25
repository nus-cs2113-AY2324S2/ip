import java.util.Scanner;

/**
 * Ui class handles the user interface interactions for the Duke application.
 */
public class Ui {

    /**
     * Displays a greeting message when the Duke application starts.
     */
    public void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm LeoDas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a line separator for better visual separation in the console.
     */
    public void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when there is an issue loading tasks from a file.
     */
    public void loading_error() {
        System.out.println("Error loading tasks from file. Starting with an empty list.");
    }

    /**
     * Displays an error message with proper formatting.
     *
     * @param message The error message to be displayed.
     */
    public void error(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads user input from the console.
     *
     * @return The user input as a String.
     */
    public String read_input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
