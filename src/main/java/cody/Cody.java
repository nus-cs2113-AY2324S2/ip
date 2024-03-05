package cody;

import cody.parser.Parser;
import cody.storage.Storage;
import cody.ui.Ui;

public class Cody {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.greet();
        while (true) {
            String fullCommand = ui.readCommand();
            if (fullCommand.equals("bye")) {
                ui.exit();
                break;
            }
            try {
                String response = Parser.parseCommand(fullCommand, tasks);
                ui.printMessage(response);
            } catch (CodyException e) {
                ui.printException(e);
            }
        }
        try {
            storage.save(tasks.getTasks());
        } catch (CodyException e) {
            ui.printException(e);
        }
    }

    public static void main(String[] args) {
        new Cody("data/tasks.txt").run();
    }
}
