/**
 * User interface class responsible for displaying messages to the user.
 */
public class Ui {

    /**
     * Displays a welcome message.
     *
     * @param name The name of the application.
     */
    public void showWelcomeMessage(String name) {
        System.out.println("Hello! I'm " + name);
    }

    /**
     * Displays instructions to the user.
     */
    public void showInstructions() {
        System.out.println("I can keep track of a to-do list for you! Just type what you want to add to the list.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }
}
