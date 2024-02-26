package beefy;

import java.io.IOException;
import java.util.Scanner;

import beefy.storage.Storage;
import beefy.ui.Ui;
import beefy.command.Command;
import beefy.parser.Parser;
import beefy.task.TaskList;

/**
 * Represents the Beefy chatbot application.
 * Beefy is a chatbot that helps with task management.
 */
public class Beefy {
    private TaskList userTasks;
    private boolean isExit;
    private Scanner scanner;

    /**
     * Constructs a new Duke object.
     */
    public Beefy() {
        try {
            userTasks = Storage.readDisk();
        } catch (BeefyException e) {
            Ui.printMessage(e.getMessage());
        } catch (IOException e) {
            Ui.printMessage("Whoops! An error occurred while handling the files...");
        }
        isExit = false;
        scanner = new Scanner(System.in);
    }

    /**
     * Runs the Beefy chatbot, prints welcome message, and execute user commands, until an exit command is executed.
     */
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
