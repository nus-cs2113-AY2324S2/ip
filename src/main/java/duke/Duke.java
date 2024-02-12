package duke;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        String filePath = "data/duke.txt";
        this.storage = new Storage(filePath);
        try {
            this.taskList = this.storage.getTaskList();
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            this.taskList = new TaskList();
        } catch (IOException e) {
            ui.printError("ERROR.... \n\t OOPS!!! Error occurred: " + e.getMessage());
        }
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
            } catch (IOException e) {
                ui.printError("ERROR.... \n\t OOPS!!! Error occurred: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
