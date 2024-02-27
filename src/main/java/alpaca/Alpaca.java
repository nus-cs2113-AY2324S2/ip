package alpaca;

import alpaca.storage.Storage;
import alpaca.tasks.TaskList;
import alpaca.ui.Ui;
import alpaca.parser.Parser;

public class Alpaca {
    public static void startConversation() {
        Ui ui = new Ui();
        Parser parser = new Parser(loadTask());
        ui.printGreeting();
        ui.listenForInput(parser);
    }

    public static TaskList loadTask() {
        if (!Storage.isFileExist()) {
            Storage.createEmptyFile();
            return new TaskList();
        }
        return Storage.restoreTask();
    }

    public static void main(String[] args) {
        startConversation();
    }
}
