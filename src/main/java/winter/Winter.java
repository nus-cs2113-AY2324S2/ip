package winter;

import winter.commands.Command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static winter.Parser.parse;


public class Winter {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Winter (String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run () {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.acceptInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException | UnsupportedOperationException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {

        new Winter("data/winter.txt").run();
    }


}