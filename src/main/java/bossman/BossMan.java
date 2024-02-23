package bossman;

import java.io.IOException;
import java.util.Scanner;
import bossman.command.Command;
import bossman.storage.Storage;
import bossman.exceptions.BossManExceptions;
import bossman.parser.Parser;
import bossman.task.TaskList;
import bossman.ui.Ui;

public class BossMan {
    private final Scanner SCANNER;
    private final TaskList TASK_LIST;
    private final Storage DATA_STORAGE;
    private boolean isExit;

    public BossMan() throws IOException {
        this.DATA_STORAGE = new Storage();
        this.SCANNER = new Scanner(System.in);
        this.TASK_LIST = DATA_STORAGE.TASK_LIST;
        this.isExit = false;
    }

    public void startChat() throws IOException {
        Ui.greetUser();

        while (!isExit) {
            Ui.printUser();

            String userInput = SCANNER.nextLine();

            try {
                Command userCommand = Parser.determineCommand(TASK_LIST, userInput);
                userCommand.execute();
                isExit = userCommand.isExit();
            } catch (BossManExceptions | IOException e) {
                Ui.printMessageWithSepNewLine(e.getMessage());
            }
        }

        DATA_STORAGE.saveTasksToFile();

        Ui.sayGoodbye();
    }
}