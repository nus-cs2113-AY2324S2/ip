package nick;

import nick.command.Command;
import nick.parser.Parser;
import nick.storage.Storage;
import nick.task.TaskList;
import nick.ui.Ui;

import java.io.IOException;

/**
 * The Nick class serves as the main class for Nick chatbot.
 * It includes the run method to start the chatbot.
 */
public class Nick {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Nick(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Nick chatbot.
     */
    public void run() throws NickException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_________")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NickException | NullPointerException | StringIndexOutOfBoundsException exception) {
                ui.showError();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws NickException {
        new Nick("data/nick.txt").run();
    }
}