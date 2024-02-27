package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import logic.UserInterface;
import logic.TaskManager;
import logic.Storage;

/**
 * Implementation of a chatbot that can track and manage user tasks named Dor
 */
public class Dor {

    /**
     * Main method that starts up the chatbot. Prints welcome message upon start-up and farewell message upon
     * exit. Quits the program upon failure to create or find the data file located at "./data/dor.txt"
     *
     * @param args Unused
     */
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

    /**
     * Makes the chatbot start reading user input after instantiating an UserInterface object
     *
     * @param storage The Storage object containing the data file to save to
     * @param taskManager The TaskManager object within which tasks will be managed
     */
    public static void start(Storage storage, TaskManager taskManager) {
        UserInterface UI = new UserInterface(storage, taskManager);
        UI.processInput();
    }

    /**
     * Returns a Storage object containing the data file located at "./data/dor.txt"
     *
     * @return The Storage object containing the data file
     * @throws IOException On failure to create or find the data file
     */
    private static Storage initialiseStorage() throws IOException {
        Storage storage = null;
        try {
            storage = new Storage("./data/dor.txt");
        } catch (IOException e) {
            throw new IOException();
        }
        return storage;
    }

    /**
     * Returns a task manager containing the tasks loaded from the data file, or an empty task manager if the
     * data file cannot be loaded
     *
     * @param storage The Storage object containing the data file
     * @return A TaskManager object
     */
    private static TaskManager initialiseTaskManager(Storage storage) {
        TaskManager taskManager;
        try {
            taskManager = storage.loadDataFromTextFile();
        } catch (FileNotFoundException e) {
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    /**
     * Prints welcome message
     */
    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints farewell message
     */
    private static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message prior to quitting the program due to failure to create or find the data file
     */
    private static void printErrorExitMessage() {
        System.out.println("ERROR: Could not create or find dor.txt!");
        System.out.println("Now exiting Dor...");
    }
}
