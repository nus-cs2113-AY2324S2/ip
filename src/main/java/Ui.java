import java.io.IOException;
/**
 * Handles interactions with the user.
 */
public class Ui {

    /**
     * Displays a welcome message.
     */
    public static void showWelcome() {
        System.out.println("Hello! I'm Sunny\nWhat can I do for you?");
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public static void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Displays a goodbye message.
     */
    public static void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that tasks have been successfully loaded.
     */
    public static void showTasksLoaded() {
        System.out.println("Tasks loaded successfully!");
    }

    /**
     * Displays a message indicating that tasks have been successfully saved.
     */
    public static void showTasksSaved() {
        System.out.println("Tasks saved successfully!");
    }

    public static void handleErrors(Exception e) {
        if (e instanceof IOException) {
            System.out.println("OOPS!!! An error occurred while handling the file.");
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The command seems to be incomplete or incorrect.");
        } else if (e instanceof NumberFormatException) {
            System.out.println("OOPS!!! Please provide a valid task index.");
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } else {
            System.out.println("OOPS!!! I'm sorry, but I encountered an unexpected error.");

        }
        //e.printStackTrace(); // Print stack trace for debugging purposes
        System.out.println(" ");
    }

    // Other UI-related methods
}

