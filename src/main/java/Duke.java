import data.Storage;
import parser.Parser;
import ui.Ui;
import task.TaskList;

import exception.DukeException;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new instance of Duke.
     *
     * @param filePath The file path for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui("aoliba");
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasksFromFile();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            ui.showLine();
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.greet();

        String command = ui.readCommand();
        while (!command.equals("bye")) {
            Parser.parse(command, tasks, ui);
            command = ui.readCommand();
        }

        storage.saveTasksToFile(tasks.getTasks());
        ui.exit();
    }

    /**
     * The main entry point of the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}