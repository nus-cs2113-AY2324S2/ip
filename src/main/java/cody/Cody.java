package cody;

import cody.parser.Parser;
import cody.storage.Storage;
import cody.ui.Ui;

/**
 * The Cody class is the main class of the application, responsible for initializing the application
 * and handling the main loop for user input and command execution.
 */
public class Cody {
    private static final String EXIT_COMMAND = "bye";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Cody instance with the specified file path for storing tasks.
     *
     * @param filePath The file path for storing tasks.
     */
    public Cody(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (CodyException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts the application, displaying a greeting message and entering a loop to read and execute commands
     * until the "bye" command is entered.
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            String fullCommand = ui.readCommand();
            isRunning = !fullCommand.equals(EXIT_COMMAND);
            if (isRunning) {
                executeCommand(fullCommand);
            }
        }
        saveTasks();
        ui.exit();
    }

    private void executeCommand(String command) {
        try {
            String response = Parser.parseCommand(command, tasks);
            ui.printMessage(response);
        } catch (CodyException e) {
            ui.printException(e);
        }
    }

    private void saveTasks() {
        try {
            storage.save(tasks.getTasks());
        } catch (CodyException e) {
            ui.printException(e);
        }
    }

    /**
     * The main method of the application, which creates a new Cody instance and starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args)  {
        new Cody("../data/tasks.txt").run();
    }
}
