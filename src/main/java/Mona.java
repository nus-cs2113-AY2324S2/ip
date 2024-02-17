import mona.input.InputParser;
import mona.output.ConsolePrint;
import mona.manager.TaskManager;
import mona.util.Constants;

import java.util.Scanner;
public class Mona {
    public static void main(String[] args) {

        ConsolePrint.greet();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        TaskManager taskManager = new TaskManager();

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
