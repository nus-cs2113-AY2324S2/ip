import Helper.CommandHandler;
import Helper.TaskManager;
import Helper.UserInterface;

import java.util.Scanner;

/**
 * The Zoro class serves as the main entry point for the application.
 * It initializes the necessary components and starts the command loop.
 */
public class Zoro {

    public static final String BYE = "bye";

    /**
     * The main method of the application.
     * Initializes the TaskManager, UserInterface,Scanner and CommandHandler.
     * Starts the command loop to handle user inputs.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        UserInterface.greetUser();

        CommandHandler commandHandler = new CommandHandler(taskManager);
        boolean isRunning = true;
        while (isRunning) {
            String input = UserInterface.getUserInput(in);
            commandHandler.handleCommand(input);
            if (input.equalsIgnoreCase(BYE)) {
                isRunning = false;
            }
        }
        in.close();
    }
}
