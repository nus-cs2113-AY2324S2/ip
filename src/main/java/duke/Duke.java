package duke;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
    }

    private void run() {
        ui.printHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
