package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import logic.UserInterface;
import logic.TaskManager;
import logic.Storage;
import tasks.Task;

public class Dor {
    public static void main(String[] args) {
        printWelcomeMessage();
        Storage storage = null;
        try {
            storage = initialiseStorage();
        } catch (IOException e) {
            printErrorExitMessage();
            return;
        }
        TaskManager taskManager = initialiseTaskManager(storage);
        start(storage, taskManager);
        printByeMessage();
    }

    public static void start(Storage storage, TaskManager taskManager) {
        UserInterface UI = new UserInterface(storage, taskManager);
        UI.processInput();
    }

    private static Storage initialiseStorage() throws IOException {
        Storage storage = null;
        try {
            storage = new Storage("./data/dor.txt");
        } catch (IOException e) {
            throw new IOException();
        }
        return storage;
    }

    private static TaskManager initialiseTaskManager(Storage storage) {
        TaskManager taskManager;
        try {
            taskManager = storage.loadDataFromTextFile();
        } catch (FileNotFoundException e) {
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
    }

    private static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printErrorExitMessage() {
        System.out.println("ERROR: Could not create or find dor.txt!");
        System.out.println("Now exiting Dor...");
    }
}
