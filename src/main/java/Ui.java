import java.util.Scanner;

/**
 * The Ui class handles user interface interactions.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    private Scanner in = new Scanner(System.in);

    /**
     * The ASCII art logo displayed when the program starts.
     */
    private final static String logo =
                    "     ░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░▒▓███████▓▒░  \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓███████▓▒░░▒▓████████▓▒░▒▓███████▓▒░  ░▒▓█▓▒░     \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░  ";

    /**
     * Greets the user when the program starts.
     */
    public void greetUser() {
        System.out.println(logo + "\nHello! I'm Bartholomew, but you can call me Bart for short :)");
        System.out.println("What can I do for you?\nType 'help' for a list of available commands!\n" + LINE);
    }

    /**
     * Bids farewell to the user when the program ends.
     */
    public void byeUser() {
        System.out.println(LINE + "\nBye. Hope to see you again soon!\n" + LINE);
    }

    /**
     * Prints a string followed by a new line.
     * @param s The string to be printed.
     */
    public void println(String s) {
        System.out.println(s);
    }

    /**
     * Retrieves input from the user.
     * @return The user's input as a string.
     */
    public String getInput() {
        return in.nextLine().trim();
    }
}
