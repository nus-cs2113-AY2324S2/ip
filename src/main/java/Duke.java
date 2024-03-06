import data.Storage;
import parser.Parser;
import ui.Ui;
import task.*; // Import the missing TaskList class

import exception.DukeException;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.greet();

        String command = ui.readCommand();
        while (!command.equals("bye")) {
            Parser.parse(command, tasks, ui, storage);
            command = ui.readCommand();
        }

        storage.saveTasksToFile(tasks.getTasks());
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}