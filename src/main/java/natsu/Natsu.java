package natsu;

import natsu.util.Parser;
import natsu.util.Ui;

import java.util.Scanner;

import static natsu.util.Storage.readFile;

/**
 * The main class of the Natsu application, responsible for initiating
 * and managing the application's run loop.
 * This class initializes the application, processes user input, and
 * delegates command execution to the {@link Parser} class.
 */
public class Natsu {

    /**
     * Initiates and manages the application's main loop.
     * This method starts the application, displays a welcome message,
     * reads the saved tasks from file, then continuously listens for and
     * processes user input until the "bye" command is received.
     */
    public void run() {
        Ui.printWelcomeMessage();
        try (Scanner input = new Scanner(System.in)) {
            boolean isActive = true;
            readFile(); // Load tasks from file at the start
            while (isActive) {
                String userInput = input.nextLine();
                isActive = Parser.executeCommand(userInput);
            }
        }
    }

    /**
     * The entry point of the Natsu application.
     * Creates an instance of {@code Natsu} and calls the {@code run} method to start the application.
     *
     * @param args The command-line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        new Natsu().run();
    }
}
