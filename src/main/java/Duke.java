
/**
 * Duke is a task management application that helps users manage their tasks.
 * It allows users to add, delete, mark as done, and list tasks.
 * Duke stores tasks in a file and loads them upon startup.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     * Displays welcome message, handles user commands until exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                ui.showLine(); // show the divider line ("_______")
                isExit = parser.parseCommand(fullCommand, tasks);
            } catch (DukeException e) {
                ui.printMessage("Error: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to start the Duke application.
     * Creates a new instance of Duke and runs it.
     *
     * @param args Command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Duke("data/taskCategory.txt").run();
    }
}