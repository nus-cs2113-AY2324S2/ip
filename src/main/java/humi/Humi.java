package humi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Entry point of the Address Book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Humi {
    private static Ui ui;
    private static TaskManager taskManager;
    private static Storage storage;

    public Humi(String folderPath, String filePath) {
        ui = new Ui();
        storage = new Storage();
        taskManager = new TaskManager(storage);
        try {
            ArrayList<String> inputFile = storage.readFile(filePath);
            if (!inputFile.isEmpty()) {
                for (String s : inputFile) {
                    taskManager.loadTask(s);
                    storage.appendTextContent(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, attempting to create file.");
            storage.createFile(folderPath, filePath);
        }
    }

    /**
     * Read user input and handle command until the program terminates.
     * Save data in the text file when the user type "bye".
     */
    public void run() {
        ui.printWelcome();

        // read user input
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            taskManager.handleCommand(input);
            input = in.nextLine();
        }

        // write text file
        try {
            storage.writeFile("data/list.txt", storage.textContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        ui.printExit();
    }

    public static void main(String[] args) {
        new Humi("./data", "data/list.txt").run();
    }
}
