import java.io.IOException; // Make sure to import IOException
import java.util.ArrayList;

/**
 * The entry point of the application. This class orchestrates the flow of the application,
 * including initializing components, loading tasks from storage, and processing user commands.
 */
public class TaskManager {

    /**
     * The main method that drives the application. It initializes the necessary components
     * such as Ui, Storage, and TaskList, and enters a loop to accept and process user commands
     * until the "bye" command is entered.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Ui ui = new Ui(); // Component for user interactions.
        Storage storage = new Storage("./data/duke.txt"); // Component for handling task storage.
        ArrayList<Task> loadedTasks = storage.load(); // Load tasks from the specified storage.
        TaskList taskList = new TaskList(loadedTasks); // Initialize the task list with loaded tasks.
        CommandParser commandParser = new CommandParser(taskList, ui); // Initialize the command parser with the task list and UI.

        ui.showWelcome(); // Display a welcome message to the user.

        boolean isExit = false; // Flag to control the main application loop.
        while (!isExit) {
            String userInput = ui.readCommand(); // Read user input.
            if (userInput.equalsIgnoreCase("bye")) {
                ui.showGoodbye(); // Display a goodbye message.
                try {
                    storage.save(taskList.getTasks()); // Attempt to save the current state of tasks.
                } catch (IOException e) { // Handle potential IO exceptions from saving tasks.
                    ui.showError("An error occurred while saving tasks: " + e.getMessage());
                }
                isExit = true; // Set flag to exit the application loop.
            } else {
                commandParser.parseCommand(userInput); // Parse and execute the user command.
            }
        }
    }
}
