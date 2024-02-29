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
    private Storage storage;

    public Kyrene() {
        storage = new Storage(FILE_PATH, FOLDER_PATH);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            storage.createFile();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = Ui.readCommand();
            Ui.showDivider();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException c) {
                Ui.showErrorInvalidCommand();
            }

        }
    }

    public static void main(String[] args) {
        new Kyrene().run();
    }

}
