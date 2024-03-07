package schmidt.core;

import schmidt.command.Command;
import schmidt.exception.SchmidtException;
import schmidt.parser.Parser;
import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * This is the class for Schmidt, a multi-functional chatbot
 */
public class Schmidt {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Schmidt(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SchmidtException e) {
            ui.printWithLines(e.getMessage());
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) {
        new Schmidt("data/tasks.txt").run();
    }
}