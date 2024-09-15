package alpaca;

import alpaca.storage.Storage;
import alpaca.tasks.TaskList;
import alpaca.ui.Ui;
import alpaca.parser.Parser;

/**
 * Entry point of the application managing user interactions and task list operations.
 */
public class Alpaca {
    /**
     * Initializes user interface and begins listening for user input.
     */
    public static void startConversation() {
        Ui ui = new Ui();
        Parser parser = new Parser(loadTask());
        ui.printGreeting();
        ui.listenForInput(parser);
    }

    /**
     * Loads tasks from storage, creating a new file if necessary.
     *
     * @return Loaded or new task list.
     */
    public static TaskList loadTask() {
        if (!Storage.isFileExist()) {
            Storage.createEmptyFile();
            return new TaskList();
        }
        return Storage.restoreTask();
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        startConversation();
    }
}
