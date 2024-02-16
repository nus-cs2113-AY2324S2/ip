package duke;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {
    private TaskList taskList;
    private final Ui UI;
    private final Storage STORAGE;

    public Duke() {
        this.UI = new Ui();
        String filePath = "data/duke.txt";
        this.STORAGE = new Storage(filePath);
        try {
            this.taskList = this.STORAGE.getTaskList();
        } catch (DukeException e) {
            UI.printError(e.getMessage());
            this.taskList = new TaskList();
        } catch (IOException e) {
            UI.printError("ERROR.... \n\t OOPS!!! Error occurred: " + e.getMessage());
        }
    }

    private void run() {
        UI.printHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = UI.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(taskList, UI, STORAGE);
                isExit = command.isExit();
            } catch (DukeException e) {
                UI.printError(e.getMessage());
            } catch (IOException e) {
                UI.printError("ERROR.... \n\t OOPS!!! Error occurred: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
