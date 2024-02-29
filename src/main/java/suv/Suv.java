package suv;

import suv.Command.SuvException;
import suv.Helpers.Ui;
import suv.Task.FileStorage;
import suv.Helpers.Parser;

import java.util.Scanner;

/**
 * The main class of the Suv chat bot application, responsible for initiating
 * and managing the application's run loop.
 * After initialization, it processes user input and
 * passes command execution to the {@link Parser} class.
 */
public class Suv {
    final static String LINE = "____________________________________________________________\n";

    /**
     * Manages the main loop of the application.
     * This method initializes the application,shows a welcome message,
     * retrieves saved tasks from file, and then continually awaits and
     * handles user input until the "bye" command is keyed in.
     */
    public static void run() throws SuvException {
        Scanner in = new Scanner(System.in);
        Ui.printWelcomeMessage();

        Parser parser = new Parser();

        String input = in.nextLine();
        FileStorage.fetchData();
        while(!input.equals("bye")) {
            parser.handleInput(input);
            input = in.nextLine();
        }
    }

    /**
     * The starting point of the Suv chat bot application.
     * Instantiates a Suv object and invokes its run method to begin the application.
     *
     * @param args The command-line arguments provided to the application (unused).
     */
    public static void main(String[] args) throws SuvException {
        new Suv().run();
    }
}