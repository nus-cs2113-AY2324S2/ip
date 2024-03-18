package ava;

import ava.parser.Parser;
import ava.storage.Storage;
import ava.tasklist.TaskList;
import ava.ui.Ui;

/**
 * Entry point of the Address Book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Ava {

    protected final Storage storage;
    protected final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Ava(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(ui);
        parser = new Parser();
        storage.loadFile(tasks);
    }

    public static void main(String[] args) {
        new Ava("./data/ava.txt").run();
    }

    /**
     * Runs the chatbot until termination.
     */
    public void run() {
        ui.greet();
        mainProcess();
        ui.exit();
    }

    /**
     * Reads and executes command repeatedly until parser.isExit() is true.
     * parser.isExit() returns true when a "bye" command is read.
     */
    public void mainProcess() {
        while (!parser.isExit()) {
            String task = ui.getUserCommand();
            ui.printLine();
            parser.parseCommand(task, tasks, storage, ui);
            if (parser.isExit()) {
                return;
            }
            ui.printLine();
        }
    }
}
