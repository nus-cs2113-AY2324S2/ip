
import java.util.Scanner;

import jason.errorhandling.JasonException;

/**
 * The entry point of the application.
 * This class initializes the application and handles the main loop that accepts user input
 * and performs actions based on that input.
 */
public class Jason {
    private static final TaskList taskList = new TaskList();

    /**
     * The main method to start the application.
     * It loads tasks from storage, displays a welcome message, and enters a loop
     * to accept and process user commands until the "bye" command is given.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        try {
            Storage.load(taskList); // Use Storage to load tasks


        } catch (JasonException e) {
            Ui.showError("Failed to load tasks: " + e.getMessage());
        }

        Ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = Ui.readCommand();

            if (input.equalsIgnoreCase("bye")) {
                try {
                    Storage.save(taskList.getTasks());
                } catch (JasonException e) {
                    Ui.showError("Failed to save tasks: " + e.getMessage());
                }
                Ui.showGoodbye();
                break;
            }

            Parser.executeCommand(input, taskList);
        }

        scanner.close();
    }
}
