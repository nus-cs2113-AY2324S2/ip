/**
 * Duke class represents the main application that manages tasks using a command-line interface.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     * @param filePath File path for storing and loading tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loading_error();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application, processing user commands until the exit command is given.
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.read_input();
                ui.line();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks.getTasks());

            } catch (DukeException e) {
                ui.error(e.getMessage());
            } finally {
                ui.line();
            }
        }
    }

    /**
     * Main method to start the Duke application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
