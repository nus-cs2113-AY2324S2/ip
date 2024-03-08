import java.util.Scanner;

/**
 * Represents the user interface of the program
 * A <code>Ui</code> object represents the user interface of the program
 */
public class Ui {
    public static final String LINE_DIVIDER = "----------------------------------------------------";
    private Scanner input = new Scanner(System.in);

    /**
     * Print the starting message
     */
    public void printStartingMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm Chandler. Your personal sarcastic task manager.");
        System.out.println("I'm here to help you manage your tasks. :)");
        System.out.println("To view the list of commands, you can type 'help'.");
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Print the ending message
     */
    public void printEndingMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Meeting you was okay..");
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Get the input from the user
     *
     * @return the input from the user
     */
    public String getInput() {
        return input.nextLine();
    }

    /**
     * Close the Scanner input
     */
    public void closeScanner() {
        input.close();
    }
}

