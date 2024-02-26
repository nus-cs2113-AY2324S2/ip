package kyrene;

import kyrene.command.Command;
import kyrene.parser.Parser;
import kyrene.taskList.TaskList;
import kyrene.ui.Ui;
import kyrene.storage.Storage;

import java.io.FileNotFoundException;

public class Kyrene {
    private final static String FILE_PATH = "./data/Kyrene.txt";
    private final static String FOLDER_PATH = "./data/";

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Kyrene() {
        ui = new Ui();
        storage = new Storage(FILE_PATH, FOLDER_PATH);
        try {
            tasks = storage.load(ui);
        } catch (FileNotFoundException e) {
            storage.createFile(ui);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showDivider();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Kyrene().run();
    }

}
