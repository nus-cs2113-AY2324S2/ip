import static Parser.Parser.chat;

import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.FileNotFoundException;

/**
 * Represents the PeeKay bot and its methods.
 */
public class PeeKay {

    private TaskList tasks;

    /**
     * Instantiates a new iteration of PeeKay app. Creates a TaskList containing existing tasks from
     * the storage file, if any. Else, creates an empty TaskList.
     * @param filepath path of the storage file containing the existing tasks
     */
    public PeeKay (String filepath) {
        try {
            tasks = new TaskList(Storage.load(filepath));
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            Ui.printLoadingErrorMessage();
        }
        Parser parser = new Parser(filepath,tasks);
    }

    /**
     * Start the app, and checks for commands.
     */
    public void run() {
        Ui.showLine();
        Ui.sayHi();
        Ui.showLine();
        chat();
    }
    public static void main(String[] args) {
        new PeeKay("src/data/peekay.txt").run();
    }
}