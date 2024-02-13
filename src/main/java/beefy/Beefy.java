package beefy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import beefy.storage.Storage;
import beefy.ui.Ui;
import beefy.command.Command;
import beefy.parser.Parser;
import beefy.task.TaskList;

public class Beefy {
    private TaskList userTasks;
    private boolean isExit;
    private Scanner scanner;

    public Beefy() {
        try {
            userTasks = Storage.readDisk();
        } catch (FileNotFoundException e) {
            Ui.printMessage("Storage file not found, creating new empty task List...");
            userTasks = new TaskList();
        } catch (BeefyException e) {
            Ui.printMessage(e.getMessage());
        }
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
                isExit = userCommand.isExit();
            } catch (BeefyException e) {
                Ui.printMessage(e.getMessage());
            } catch (IOException e) {
                Ui.printMessage("Whoops! An error occurred while handling the files...");
            }
        }
        Ui.printBye();
    }

    public static void main(String[] args) {
        Beefy chatBot = new Beefy();
        chatBot.run();
    }
}
