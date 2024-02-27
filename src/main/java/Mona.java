import mona.input.Parser;
import mona.input.Ui;
import mona.output.ConsolePrint;
import mona.manager.TaskList;
import mona.util.Constants;

import mona.storage.Storage;

public class Mona {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Mona() {
        this.ui = new Ui();
        this.storage = new Storage(Constants.DATA_FILE_PATH);
        this.taskList = new TaskList((this.storage).loadData());
    }

    public void run() {
        String userCommand = ui.getUserInput();

        while (!userCommand.equals("bye")) {
            Parser inputParser = new Parser(userCommand);

            if (inputParser.isValidInput()) {
                taskList.executeCommand(inputParser.getCommandTypeAndParams());
                storage.saveToStorage(taskList.getTasks());
            }

            userCommand = ui.getUserInput();
        }
        ConsolePrint.exit();
    }
    public static void main(String[] args) {
        new Mona().run();
    }
}
