import command.Command;
import commandexceptions.InvalidDeadlineCommandException;
import commandexceptions.InvalidEventCommandException;
import commandexceptions.InvalidTodoCommandException;
import commandexceptions.JingHaoExceptions;

import parser.Parser;
import tasktype.Deadline;
import tasktype.Event;
import tasktype.Task;
import tasktype.Todo;
import tasktype.TaskList;

import storage.Storage;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class JingHao {

    protected static Ui ui;
    protected static TaskList taskList;
    protected final Storage storage;

    public JingHao(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = this.storage.readFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return;
        } catch (JingHaoExceptions e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

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
