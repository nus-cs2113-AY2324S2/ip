import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private static final String NAME = "Jonas";
    private static final String LINE = "____________________________________________________________";

    private Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets user input from the console.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME + ".");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showByeMessage() {
        System.out.println(LINE);
        System.out.println("Kamxia. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(LINE);
        System.out.println("OOPS!!! " + errorMessage);
        System.out.println(LINE);
    }

    /**
     * Closes the scanner used to get user input.
     */
    public void closeScanner() {
        scanner.close();
    }
}
