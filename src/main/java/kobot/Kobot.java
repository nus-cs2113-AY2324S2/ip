package kobot;

import kobot.task.TaskList;
import kobot.storage.Storage;

public class Kobot {
    TaskList taskList;
    Command command;
    
    public Kobot() {
        this.taskList = Storage.loadFile();
        this.command = new Command();
    }
    
    public void run() {
        Ui.printHelloMessage();

        while (!command.getIsExit()) {
            String input = Ui.receiveInput();
            command.parseCommand(input);
            command.executeCommand(taskList);
        }

        Ui.printGoodbyeMessage();
    }
}
