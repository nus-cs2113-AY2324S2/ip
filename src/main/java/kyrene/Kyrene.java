package kyrene;

import kyrene.command.Command;
import kyrene.parser.Parser;
import kyrene.taskList.TaskList;
import kyrene.ui.Ui;
import kyrene.storage.Storage;

import java.io.FileNotFoundException;

/**
 * The main body of the ChatBot Kyrene. It initialises from loading a file to get existing tasks,
 * and then interacts with user to manage the to-do list.
 */
public class Kyrene {
    private final static String FILE_PATH = "./data/Kyrene.txt";
    private final static String FOLDER_PATH = "./data/";

    private TaskList tasks;
    private Storage storage;

    /**
     * Initialise Kyrene with loading file from the specified file path.
     * If the file does not exist, then create one.
     */
    public Kyrene() {
        storage = new Storage(FILE_PATH, FOLDER_PATH);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            storage.createFile();
            tasks = new TaskList();
        }
    }

    /**
     * Run Kyrene to interact with user.
     * Exit when user commands to exit.
     */
    public void run() {
        Ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = Ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException c) {
                Ui.showErrorInvalidCommand();
            }

        }
    }

    /**
     * Main function to initialise and run Kyrene.
     */
    public static void main(String[] args) {
        new Kyrene().run();
    }

}
