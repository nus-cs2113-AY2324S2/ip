package Blue;

import java.util.Scanner;

/**
 * Responsible for interacting with the user through standard input/output.
 */
public class Ui {
    private static Scanner in = new Scanner(System.in);
    private static InputParser parser = new InputParser();

    /**
     * Prints some dialogue to standard output, acknowledging handling of user request.
     * 
     * @param line The line of dialogue to print.
     */
    public static void talk(String line) {
        System.out.println(line);
    }

    /**
     * Prints some warning to standard output, informing user of a bad request.
     * 
     * @param line The line of warning to print.
     */
    public static void warn(String line) {
        System.out.println(line);
    }

    /**
     * Returns a well-formed request parsed from user input.
     *
     * @return A properly instantiated Input object containing some valid request.
     */
    public static Input getRequest() {
        String line = in.nextLine();
        parser.parse(line);
        Input userInput = parser.getParsedInput();
        return userInput;
    }
}
