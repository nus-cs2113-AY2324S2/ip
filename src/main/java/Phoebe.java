import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main class for the Phoebe task management application.
 * Initializes the application, loads tasks from storage, and handles user input until termination.
 */
public class Phoebe {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Phoebe application instance with the specified file path for storage.
     * Loads tasks from the given file path into the task list.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Phoebe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (PhoebeException e) {
            Ui.printError();
        }
    }

    /**
     * Runs the main loop of the application and runs the user inputs until the "bye" command is received.
     * Saves tasks to storage upon exit ONLY when via "bye" command.
     */
    public void run() {
        Ui.printGreeting();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();
            isRunning = Parser.parseCommand(input, tasks, ui);
        }
        try {
            storage.saveTasks(Objects.requireNonNull(TaskList.getTasks()));
        } catch (IOException e) {
            Ui.printError();
        }
    }

    /**
     * Entry point for Phoebe.
     * Creates instance of Phoebe and runs it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Phoebe("phoebe.txt").run();
    }
}

