package blue.ui;

import blue.exception.IllegalInputException;
import blue.command.Input;

import java.util.Scanner;

/**
 * Responsible for all user facing interactions through standard input/output.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to Blue, a command line task assistant.";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";
    private static final String LINE_DIVIDER = "-------------------------------------------------------------";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static Scanner in;
    private static InputParser parser;
    private static Input request;
    private static boolean isUserActive;

    /**
     * Constructor for Ui.
     * Note that it need only be called once as it suffices to have one UI per program.
     */
    public Ui() {
        in = new Scanner(System.in);
        parser = new InputParser(); 
        isUserActive = true;
    }

    public Input getRequest() {
        return request;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void greet() {
        talk(WELCOME_MESSAGE, ANSI_BLUE);
    }

    public void farewell() {
        talk(GOODBYE_MESSAGE, ANSI_BLUE);
    }

    /**
     * Receives the next user request, parsing it into request if well formed and throwing an exception otherwise.
     *
     * @throws IllegalInputException If user request is misformed in any way.
     */
    public void nextRequest() throws IllegalInputException {
        String line = in.nextLine();
        try {
            request = parser.parse(line);
        } catch (IllegalInputException e) {
            throw e;
        }
        isUserActive = request.isNotExit();
    }

    /**
     * Prints a single line of dialogue to standard output, usually acknowledging proper handling of request.
     * 
     * @param line The line of dialogue to print.
     */
    public void talk(String line) {
        printDivider();
        System.out.println(" " + line);
        printDivider();
    }

    /**
     * Prints a single line of dialogue to standard output, usually acknowledging proper handling of request.
     * 
     * @param line The line of dialogue to print.
     * @param colour What to colour this line of text.
     */
    public void talk(String line, String colour) {
        talk(colour + line + ANSI_RESET);
    }

    /**
     * Prints the divider.
     */
    private void printDivider() {
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Prints multiple lines of dialogue to standard output, usually as an explicit response to a request.
     * 
     * @param lines The lines of dialogue to print.
     * @param isNumbered Whether to prefix each line with a line number.
     */
    public void talk(String[] lines, boolean isNumbered) {
        printDivider();
        int lineNumber = 1;
        for (String line : lines) {
            if (isNumbered) {
                System.out.println(" " + lineNumber + ". " + line);
                lineNumber += 1;
            } else {
                System.out.println(" " + line);
            }
        }
        printDivider();
    }

    /**
     * Prints some warning to standard output, informing user of a bad request.
     * 
     * @param line The line of warning to print.
     */
    public void warn(String line) {
        talk(line, ANSI_RED);
    }
}
