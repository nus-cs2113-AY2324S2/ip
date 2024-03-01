package mona.input;

import mona.output.ConsolePrint;

import java.util.Scanner;

/**
 * Handles user interface interactions, mainly input operations.
 */
public class Ui {
    protected Scanner in;
    /**
     * Constructor for Ui. Initializes the Scanner and displays a greeting message.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        ConsolePrint.greet();
    }
    /**
     * Reads and returns the user's input from the console, trimming any leading and trailing whitespace.
     *
     * @return A trimmed string of the user's input.
     */
    public String getUserInput() {
        return in.nextLine().trim();
    }
}
