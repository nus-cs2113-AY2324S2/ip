import command.Command;
import commandexceptions.JingHaoExceptions;

import parser.Parser;
import tasktype.TaskList;

import storage.Storage;
import ui.Ui;

import java.io.IOException;

/**
 * Represents the JingHao CLI chatbot application.
 * JingHao allows users to track and manage their tasks, as well as to store tasks in data storage.
 */
public class JingHao {

    protected static Ui ui;
    protected static TaskList taskList;
    protected final Storage storage;

    /**
     * Constructs a BossMan instance.
     * Initializes the TaskList and Storage objects.
     */
    public JingHao(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = this.storage.readFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (JingHaoExceptions e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Starts the JingHao chatbot application.
     * Displays a greeting message to the user, reads user input,
     * execute user commands, and saves tasks to file.
     */
    public void start() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.promptUser();
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(taskList,ui,storage);
                isExit = c.isExit();
                storage.updateDisk(taskList);
            } catch (JingHaoExceptions e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IOException e) {
                ui.printErrorMessage("Error occurred: " + e.getMessage());
            }
        }
    }
}
