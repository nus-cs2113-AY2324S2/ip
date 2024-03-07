import java.util.Scanner;

/**
 * Handle UI aspect of Stella
 */
public class UI {
    public static final String LINE_SEPARATOR = "--------------------------------------";

    /**
     * Print welcome message
     */
    public static void welcome () {
        String name = "Stella";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(UI.LINE_SEPARATOR);
    }

    /**
     * Print a particular message with 2 line separator
     * to the terminal.
     * @param message the message to be displayed.
     */
    public static void printMessage(String message){
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(UI.LINE_SEPARATOR);
    }

    /**
     * Print a goodbye message to the terminal.
     */
    public static void bye () {
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(UI.LINE_SEPARATOR);
    }

}
