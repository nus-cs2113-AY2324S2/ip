package bob.utils;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * User Interface which interacts with the User.
 * Retrieves user input from standard input, and prints output to standard output.
 */
public class Ui {
    private static final String WELCOME = " Hello! I'm Bob\n What can I do for you?";
    private static final String EXIT = " Bye. Hope to see you again soon!";
    private static final String SEPARATOR = "____________________________________________________________";
    private final Scanner inputReader;

    /**
     * Creates a new Ui Object.
     */
    public Ui() {
        inputReader = new Scanner(System.in);
    }

    /**
     * Reads user commands from standard input. Returns the user command as an uppercase String.
     *
     * @return User command as an uppercase String.
     */
    public String readCommand() {
        return inputReader.next().toUpperCase();
    }

    /**
     * Clears current line of input by skipping to the next line.
     */
    public void clearInput() {
        // Force Scanner to jump to next line
        inputReader.nextLine();
    }

    /**
     * Checks if there is more input.
     * Returns true if there is more input to read.
     *
     * @return Boolean value indicating if there is more input.
     */
    public boolean hasMoreInput() {
        return inputReader.hasNext();
    }

    /**
     * Finds a pattern in the input line using the provided regular expression pattern.
     *
     * @param regex Regular expression pattern to find.
     */
    public void findInLine(String regex) {
        inputReader.findInLine(regex);
    }

    /**
     * Attempts to return a match result from a regular expression scan.
     * Returns the matched result, as per the provided regular expression String.
     *
     * @return Match result from the previous regular expression scan.
     */
    public MatchResult match() {
        return inputReader.match();
    }

    /**
     * Prints Bob Chat-Bot logo.
     */
    public void printLogo() {
        String bobAsciiLogo = " ____        _\n"
                + "|  _ \\      | |\n"
                + "| |_) | ___ | |__\n"
                + "|  _ < / _ \\| '_ \\\n"
                + "| |_) | (_) | |_) |\n"
                + "|____/ \\___/|_.__/\n";

        System.out.print(bobAsciiLogo);
    }

    /**
     * Formats and prints provided output String.
     *
     * @param output Output String to be printed.
     */
    public void print(String output) {
        System.out.println(Ui.SEPARATOR);
        System.out.println(output);
        System.out.println(Ui.SEPARATOR);
    }

    /**
     * Prints Welcome message.
     */
    public void printWelcome() {
        print(Ui.WELCOME);
    }

    /**
     * Prints Exit message.
     */
    public void printExit() {
        print(Ui.EXIT);
    }
}
