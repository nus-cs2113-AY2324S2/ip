package Byte;

import Byte.exception.ByteException;
import Byte.parser.Parser;
import Byte.storage.Storage;
import Byte.task.TaskList;
import Byte.ui.Ui;

import java.io.IOException;
import java.util.Scanner;


public class Byte {
    private static final String FILE_PATH = "./data/byte.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

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

    public static void main(String[] args) {

        new Byte(FILE_PATH).run();
    }

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


