import mona.input.InputParser;
import mona.input.Ui;
import mona.output.ConsolePrint;
import mona.manager.TaskManager;
import mona.task.Task;
import mona.util.Constants;

import mona.storage.Storage;

import java.util.Scanner;
public class Mona {

    private Ui ui;
    private Storage storage;
    private TaskManager taskManager;

    public Mona() {
        this.ui = new Ui();
        this.storage = new Storage(Constants.DATA_FILE_PATH);
        this.taskManager = new TaskManager((this.storage).loadData());
    }

    public void run() {
        String userCommand = ui.getUserInput();

        while (!userCommand.equals("bye")) {
            InputParser input = new InputParser(userCommand);

            if (input.isValidInput()) {
                taskManager.executeCommand(input.getCommandTypeAndParams());
                storage.saveToStorage(taskManager.getTasks());
            }

            userCommand = ui.getUserInput();
        }
        ConsolePrint.exit();
    }
    public static void main(String[] args) {
        new Mona().run();
    }
}
