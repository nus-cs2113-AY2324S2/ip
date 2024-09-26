package Chat.tasks;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;
import Chat.exceptions.InvalidIndex;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;
import java.io.File;


public class Evelyn {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Create an instance of Evelyn class.
     * Initialise the Ui and Storage.
     * If there is a file for data storage, write into the file.
     * Or else create a new TaskList.
     */
    public Evelyn() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = storage.loadTasks();
        } catch (FileNotFoundException e) {
            ui.showError("File not found: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Run the application for a Ui.
     * Show a greeting message.
     * Initialise a Parser object in the task list.
     * If the user do not key in "bye", enter the loop for continuous command.
     * Save the task at the end of commands.
     * Show a bye message when exited.
     * @throws InvalidIndex if index is out of bound.
     */
    public void run() throws InvalidIndex {
        ui.showWelcomeMessage();
        Parser parser = new Parser(tasks);
        boolean isExit = false;
        while (!isExit) {
            System.out.println("type your command: ");
            String userInput = ui.getUserInput();
            Parser.parse(userInput);
            if (userInput.equals("bye")) {
                isExit = true;
            }
        }
        storage.saveTasks(tasks);
        ui.showGoodbyeMessage();
    }

    /**
     * Start running the whole program
     * @param args Takes in the command line.
     * @throws InvalidIndex If an index is invalid or out of bound.
     */
    public static void main(String[] args) throws InvalidIndex {
        new Evelyn().run();
    }

}
