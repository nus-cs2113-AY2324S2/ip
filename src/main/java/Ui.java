import java.util.Scanner;

import exceptions.WallybotException;


/**
 * Deals with interactions with the user.
 */
public class Ui {
    // ATTRIBUTES
    public static final String TEXTDIVIDER = "- - - - - - - - - - - - -";
    private Scanner in;

    // METHODS
    /**
     * Constructs Ui and initialises Scanner to read input.
     */
    public Ui() {
        in = new Scanner(System.in);
        showWelcome();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        final String LOGO =
                " _       __      ____      __          __ \n" +
                "| |     / /___ _/ / /_  __/ /_  ____  / /_\n" +
                "| | /| / / __ `/ / / / / / __ \\/ __ \\/ __/\n" +
                "| |/ |/ / /_/ / / / /_/ / /_/ / /_/ / /_  \n" +
                "|__/|__/\\__,_/_/_/\\__, /_.___/\\____/\\__/  \n" +
                "                 /____/                   \n"
                ;
        final String CHATBOTNAME = "Wallybot";

        System.out.println(LOGO);
        System.out.println("Beep boop. Hello! I'm " + CHATBOTNAME + "! :P");
        System.out.println("What can I do for you today?");
        System.out.println(TEXTDIVIDER);
    }

    /**
     * Gets relevant command and input from user input.
     */
    public String[] processInput() throws WallybotException {
        String command = in.next().toLowerCase();
        String input = in.nextLine().strip();

        return new String[]{command, input};
    }

    /**
     * Prints error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints text divider for user readability.
     */
    public void showLine() {
        System.out.println(TEXTDIVIDER);
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        System.out.println("Productive day today! :D Shutting down...");
    }
}
