package lotes;

import lotes.parser.Parser;
import lotes.task.TaskList;
import lotes.ui.UserInterface;
import lotes.storage.Storage;
import lotes.storage.Storage.InvalidStorageFilePathException;

import java.util.Scanner;

public class Main {

    private UserInterface ui;
    private Storage storage;
    private TaskList taskList;
    private Scanner inputCommand;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        start();
        Parser.interpretUserInput(inputCommand, taskList);
        exit();
    }

    private void start() {
        this.ui = new UserInterface();
        this.inputCommand = new Scanner(System.in);
        this.taskList = new TaskList(); // Creating the lotes.task.TaskList object
        Storage.load();
        ui.showGreetingsMessage();
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        //UserInterface.showGoodbyeMessage();
        System.exit(0);
    }
}
