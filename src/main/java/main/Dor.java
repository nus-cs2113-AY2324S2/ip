package main;

import java.io.FileNotFoundException;
import logic.UserInterface;
import logic.TaskManager;
import logic.Storage;

public class Dor {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
        start();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void start() {
        Storage storage = new Storage("./data/dor.txt");
        TaskManager taskManager;
        try {
            taskManager = storage.loadDataFromTextFile();
        } catch (FileNotFoundException e) {
            taskManager = new TaskManager();
        }
        UserInterface UI = new UserInterface(storage, taskManager);
        UI.processInput();
    }
}
