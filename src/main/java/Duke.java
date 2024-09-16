/**
 * This class represents the main application logic for managing tasks.
 * It initializes the user interface, storage, and task list, and provides a loop for executing commands.
 * @author Your Name
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke object with the specified file path for task storage.
     * Initializes the user interface, storage, and attempts to load tasks from the specified file.
     * If loading fails, initializes an empty task list.
     * @param filePath the path to the file where tasks are stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application, continuously reading and executing commands from the user.
     * The loop continues until the user issues an exit command.
     */
    public void run() {
        ui.printHello();
        boolean isExit = false;
        for (;;) { // Infinite loop
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

                storage.save(tasks.getTasks());

                // Break out of the loop if isExit is true
                if (isExit) {
                    break;
                }
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the Duke application.
     * Creates a new Duke instance with the specified file path and runs the application.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


