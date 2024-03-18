package duke;
import java.util.Scanner;

/**
 * A class that acts as the User Interface (UI) to take in inputs from the user
 */
public class UI {
    private Parser parser = new Parser();

    /**
     * the main loop of the programme which keeps running until the user inputs 'bye'
     */
    public void mainLoop() {
        String input = "Start";
        Scanner in = new Scanner(System.in);
        while (!input.equals("bye")) {
            input  = in.nextLine();
            parser.tryParseInput(input);
        }
    }
}
