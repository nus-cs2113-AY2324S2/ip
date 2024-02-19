package kobot;

import kobot.command.Command;
import kobot.command.Parser;
import kobot.task.TaskList;
import kobot.storage.Storage;
import kobot.ui.Ui;

public class Kobot {
    TaskList taskList;
    Parser parser;
    
    public Kobot() {
        this.taskList = Storage.loadFile();
        this.parser = new Parser();
    }
    
    public void run() {
        Ui.printHelloMessage();

        while (!parser.isExit()) {
            String input = Ui.receiveInput();
            parser.parseCommand(input);
            Command command = parser.prepareCommand(taskList);
            
            if (command == null) {
                continue;
            }
            command.execute();
        }

        Ui.printGoodbyeMessage();
    }
}
