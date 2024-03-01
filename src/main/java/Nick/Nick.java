package Nick;

import Nick.command.Command;
import Nick.parser.Parser;
import Nick.storage.Storage;
import Nick.task.TaskList;
import Nick.ui.Ui;

public class Nick {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Nick(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NickException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_________")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NickException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws NickException {
        new Nick("data/nick.txt").run();
    }
}