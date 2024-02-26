package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import logic.UserInterface;
import logic.TaskManager;
import logic.Storage;

public class Dor {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
        try {
            start();
        } catch (IOException e) {
            System.out.println("ERROR: Could not create or find dor.txt!");
            System.out.println("Now exiting Dor...");
            return;
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void start() throws IOException {
        Storage storage = null;
        try {
            storage = new Storage("./data/dor.txt");
        } catch (IOException e) {
            throw new IOException();
        }
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
