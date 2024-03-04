package blue.ui;

import blue.exception.IllegalInput;
import blue.command.Input;

import java.util.Scanner;

/**
 * Responsible for all user facing interactions through standard input/output.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Greetings! I'm Blue, a command line task assistant.";
    private static final String GOODBYE_MESSAGE = "Till we meet again.";
    private static Scanner in;
    private static InputParser parser;

    /**
     * Public constructor for Ui.
     * Note that it need only be called once as it suffices to have one UI per program.
     */
    public Ui() {
        in = new Scanner(System.in);
        parser = new InputParser(); 
    }

    public void greet() {
        talk(WELCOME_MESSAGE);
    }

    public void farewell() {
        talk(GOODBYE_MESSAGE);
    }

    /**
     * Returns a well-formed request parsed from user input.
     *
     * @return A properly instantiated Input object containing some valid request.
     * @throws IllegalInput If user request is misformed in any way.
     */
    public Input getRequest() throws IllegalInput {
        String line = in.nextLine();
        try {
            Input userInput = parser.parse(line);
            return userInput;
        } catch (IllegalInput e) {
            throw e;
        }
    }

    /**
     * Prints a single line of dialogue to standard output, usually acknowledging proper handling of request.
     * 
     * @param line The line of dialogue to print.
     */
    public void talk(String line) {
        System.out.println(line);
    }

    /**
     * Prints multiple lines of dialogue to standard output, usually as an explicit response to a request.
     * 
     * @param line The line of dialogue to print.
     * @param isNumbered Whether to prefix each line with a line number.
     */
    public void talk(String[] lines, boolean isNumbered) {
        int lineNumber = 1;
        for (String line : lines) {
            if (isNumbered) {
                System.out.println(lineNumber + ". " + line);
                lineNumber += 1;
            } else {
                System.out.println(line);
            }
        }
    }

    /**
     * Prints some warning to standard output, informing user of a bad request.
     * 
     * @param line The line of warning to print.
     */
    public void warn(String line) {
        System.out.println(line);
    }
}
