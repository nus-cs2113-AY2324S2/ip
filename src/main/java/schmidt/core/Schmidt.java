package schmidt.core;

import schmidt.command.Command;
import schmidt.exception.SchmidtException;
import schmidt.parser.Parser;
import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * This is the class for Schmidt who can understand multiple commands and manage a task list for the user.
 */
public class Schmidt {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Schmidt object by initializing the user interface, task list, and storage.
     */
    public Schmidt() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (SchmidtException e) {
            ui.printWithLines(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Schmidt to read commands from the user and execute them until the user exits.
     */
    private void run() {
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SchmidtException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * The entry point for the Schmidt program.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        new Schmidt().run();
    }
}