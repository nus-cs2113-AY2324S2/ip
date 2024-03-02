package Blue;

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
     * Class level constructor for Ui.
     * Note that it should only be used once per instance of the program.
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
     */
    public Input getRequest() {
        String line = in.nextLine();
        try {
            Input userInput = parser.parse(line);
            return userInput;
        } catch (IllegalInput e) {
            warn(e.getMessage());
            return new Input(InputCommand.undefined);
        }
    }

    /**
     * Prints some dialogue to standard output, acknowledging handling of user request.
     * 
     * @param line The line of dialogue to print.
     */
    public void talk(String line) {
        System.out.println(line);
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
