package kobot;

import kobot.task.TaskList;
import kobot.storage.Storage;

public class Kobot {
    TaskList taskList;
    Ui ui;
    Command command;
    
    public Kobot() {
        this.taskList = Storage.loadFile();
        this.ui = new Ui();
        this.command = new Command();
    }
    
    public void run() {
        ui.printHelloMessage();

        while (!command.getIsExit()) {
            String input = ui.receiveInput();
            command.parseCommand(input);
            command.executeCommand(taskList, ui);
        }

        ui.printGoodbyeMessage();
    }
}
