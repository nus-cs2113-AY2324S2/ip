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
    //    public static void main(String[] args) {
//        Ui ui = new Ui();
//        Storage storage = new Storage();
//        try {
//            TaskList taskList = storage.loadTasks();
//            ui.showWelcomeMessage();
//            Parser parser = new Parser(taskList);
//            boolean isExit = false;
//            while (!isExit) {
//                System.out.println("type your command: ");
//                String userInput = ui.getUserInput();
//                parser.parse(userInput);
//                if (userInput.equals("bye")) {
//                    isExit = true;
//                }
//            }
//            storage.saveTasks(taskList);
//            ui.showGoodbyeMessage();
//        } catch (FileNotFoundException e) {
//            ui.showError("File not found: " + e.getMessage());
//        } catch (InvalidIndex e) {
//            throw new RuntimeException(e);
//        }
//    }
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

    public static void main(String[] args) throws InvalidIndex {
        new Evelyn().run();
    }

}
