/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Prints a line for better visuals
     */
    protected static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the Welcome message upon starting Nehsik
     */
    protected static void displayGreetings() {
        printLine();
        System.out.println("Hala habibi! Shlonik? Shakhbarak?");
        System.out.println("I'm Nehsik, What can I do for you?");
        printLine();
    }

    /**
     * Displays the Exit message when the user exits from the program
     */
    protected static void displayExitMessage() {
        printLine();
        System.out.println("Yalla bye. Ka Mal Lah!");
        printLine();
    }

    /**
     * Displays the error message when an exception is thrown
     * @param errorMessage The error message to be displayed to the user
     */
    protected static void displayErrorMessage(String errorMessage) {
        Ui.printLine();
        System.out.println(errorMessage);
        Ui.printLine();
    }
}
