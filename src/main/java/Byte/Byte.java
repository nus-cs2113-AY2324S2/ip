package Byte;

import Byte.exception.ByteException;
import Byte.parser.Parser;
import Byte.storage.Storage;
import Byte.task.TaskList;
import Byte.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the main class for the Byte chatbot application.
 */
public class Byte {
    private static final String FILE_PATH = "./data/byte.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a new Byte chatbot instance.
     *
     * @param filePath The file path for storing task data.
     */
    public Byte(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ByteException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method to start the Byte chatbot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {

        new Byte(FILE_PATH).run();
    }

    /**
     * Runs the Byte chatbot application.
     */
    public void run() {
        ui.printTasks(tasks);
        ui.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine();
                if (Parser.isExitCommand(userInput)) {
                    ui.printGoodbye();
                    break;
                }
                String response = Parser.parse(userInput, tasks);
                ui.printResponse(response);
            } catch (ByteException e) {
                ui.printError(e.getMessage());
            }
        }
        scanner.close();
        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            ui.printError("Error saving tasks to file.");
        }
    }
}


