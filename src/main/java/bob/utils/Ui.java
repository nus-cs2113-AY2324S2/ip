package bob.utils;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class Ui {
    private static final String WELCOME = " Hello! I'm Bob\n What can I do for you?";
    private static final String EXIT = " Bye. Hope to see you again soon!";
    private static final String SEPARATOR = "____________________________________________________________";
    private final Scanner inputReader;

    public Ui() {
        this.inputReader = new Scanner(System.in);
    }

    /**
     * Read user command (e.g., list, deadline, bye)
     */
    public String readCommand() {
        return inputReader.next().toUpperCase();
    }

    public void clearInput() {
        // Force Scanner to jump to next line
        inputReader.nextLine();
    }

    public boolean hasMoreInput() {
        return inputReader.hasNext();
    }

    public void findInLine(String regex) {
        inputReader.findInLine(regex);
    }

    public MatchResult match() {
        return inputReader.match();
    }

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
     * Generic print method for any given input
     */
    public void print(String output) {
        System.out.println(SEPARATOR);
        System.out.println(output);
        System.out.println(SEPARATOR);
    }

    public void printWelcome() {
        print(WELCOME);
    }

    public void printExit() {
        print(EXIT);
    }
}
