package beefy;

import java.util.Scanner;
import beefy.ui.Ui;
import beefy.command.Command;
import beefy.parser.Parser;
import beefy.task.TaskList;

public class Beefy {
    private TaskList userTasks;
    private boolean isExit;
    private Scanner scanner;

    public Beefy() {
        userTasks = new TaskList();
        isExit = false;
        scanner = new Scanner(System.in);
    }

    private void run () {
        Ui.printHi();
        while (!isExit) {
            Ui.printUser();
            String userInput = scanner.nextLine();
            try {
                Command userCommand = Parser.determineCommand(userTasks, userInput);
                userCommand.execute();
                this.isExit = userCommand.isExit();
            } catch (BeefyException e) {
                Ui.printMessage(e.getMessage());
            }
        }
        Ui.printBye();
    }

    public static void main(String[] args) {
        Beefy chatBot = new Beefy();
        chatBot.run();
    }
}
