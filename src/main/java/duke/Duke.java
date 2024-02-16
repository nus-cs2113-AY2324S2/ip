package duke;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents the Duke chatbot application.
 * Duke is a chatbot that helps with task management.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new Duke object.
     */
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

    /**
     * Runs the Duke chatbot, prints welcome message, and execute user commands.
     */
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

    /**
     * The main method for starting the Duke chatbot
     * Creates a new Duke object and runs the Duke chatbot
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
