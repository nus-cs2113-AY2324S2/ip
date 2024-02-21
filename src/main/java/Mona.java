import mona.input.InputParser;
import mona.output.ConsolePrint;
import mona.manager.TaskManager;
import mona.util.Constants;

import mona.storage.TaskStorage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Mona {
    public static void main(String[] args) {

        ConsolePrint.greet();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        TaskManager taskManager = new TaskManager();
        taskManager.loadFromStorage();

        while (true) {
            InputParser input = new InputParser(line);

            if (!input.isValidInput()) {
                line = in.nextLine();
                continue;
            }

            taskManager.executeCommand(input.getCommandTypeAndParams());

            if (input.getCommandTypeAndParams()[Constants.INDEX_COMMAND_TYPE].equals("bye")) {
                ConsolePrint.exit();
                break;
            }
            line = in.nextLine();
        }
    }
}
