package ip.main;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner in = new Scanner(System.in);

    private final static String logo =
            "  ____   _   _      __     ______    _       _  _____\n" +
            " / ___| | | | |    /  \\    |  _  \\  | |     | ||  ___|\n" +
            "| |     | |_| |   / /\\ \\   | |_| /  | |     | || |___\n" +
            "| |     |  _  |  / /__\\ \\  |  __ \\  | |     | ||  ___|\n" +
            "| |___  | | | | / ______ \\ | |  \\ \\ | |____ | || |___\n" +
            " \\____| |_| |_|/_/      \\_\\|_|   \\_\\|______||_||_____|\n";

    /**
     * The bot introduces itself to the user and prompts for input
     */
    public void introduce() {
        printWithoutLeadingSpace("Hello! I'm Charlie!\n" + logo);
        printWithoutLeadingSpace("What can I do for you?");
    }

    /**
     * Prints a String without leading space and with a newline at the end
     *
     * @param s String to be printed
     */
    public void printWithoutLeadingSpace(String s) {
        System.out.println(s);
    }

    /**
     * Prints a String with leading space and with a newline at the end
     *
     * @param s String to be printed
     */
    public void print(String s) {
        System.out.println("     " + s);
    }

    /**
     * Returns the user's input, with leading and trailing spaces trimmed
     *
     * @return user's input
     */
    public String getInput() {
        return in.nextLine().trim();
    }
}
