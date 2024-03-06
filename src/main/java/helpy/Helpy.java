package helpy;

import helpy.command.Command;
import helpy.task.*;

public class Helpy {
    public static String filePath = "./data/helpy.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(filePath, taskList);
        Parser parser = new Parser();

        ui.greetUser();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = parser.parse(fullCommand);
            command.execute(taskList, ui, storage);
            isExit = command.isExit();
        }
    }
}
