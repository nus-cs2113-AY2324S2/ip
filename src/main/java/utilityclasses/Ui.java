package utilityclasses;
import drosstasks.Task;
import java.util.ArrayList;

/**
 * The Ui class provides static methods to display various user interface messages for the Dross application.
 */
public class Ui {

    /**
     * Prints a line of underscore characters to visually separate message sections.
     */
    public static void printLine() {
        System.out.println("_".repeat(55));
    }

    /**
     * Displays a goodbye message to the user upon exiting the application.
     */
    public static void printGoodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    /**
     * Prints a welcome message when the application starts.
     */
    public static void printWelcomeMessage(){
        printLine();
        System.out.println("Hello! I am Dross");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Displays a message indicating that no search results were found.
     */
    public static void printEmptySearchResult(){
        printLine();
        System.out.println("yeah that stuff, it not in the list...");
        printLine();
    }

    /**
     * Shows the search results when tasks matching the search criteria are found.
     * @param matches An ArrayList of Task objects that match the search criteria.
     */
    public static void printSearchResults(ArrayList<Task> matches){
        printLine();
        System.out.println("These stuff have the same description of what you searched for or whatever");
        for (int i = 0; i < matches.size(); i++) {
            Task currentTask = matches.get(i);
            System.out.print((i + 1) + ".");
            System.out.println(currentTask);
        }
        printLine();
    }

}
