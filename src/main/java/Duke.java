import java.util.ArrayList;

/**
 * The {@code Duke} class represents the main entry point for this application.
 * The application serves as a task manager that allows users to add, delete and find tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * This constructs a new instance of Duke with the specified file path for storage.
     * Initialisezes the user interface (Ui) , storage and task list components.
     * @param filePath The path to the file used for storage of task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>()); // initialize with an empty list
        }
    }

    /*
    Starts the chatbot application. This method shows the welcome message, and then enters
    a loop to read and execute commands until the user exits
     */
    public void run() {
        ui.showWelcome("Rose");
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            try {
                isExit = Parser.parseAndExecute(userInput, tasks, ui, storage); // Assuming parseAndExecute returns a boolean to indicate exit
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner(); // Close the scanner when the program ends
    }

    /**
     * The main method serves as the program's entry point.
     * It creates a new Duke instance and calls its run method
     * @param args Command- line arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run(); // Assuming the file path to the data file
    }
}

