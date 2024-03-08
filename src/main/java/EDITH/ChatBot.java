package EDITH;

import EDITH.parserPackage.Parser;
import EDITH.storagePackage.Storage;
import EDITH.taskListPackage.TaskList;
import EDITH.ui.Ui;

import java.util.Scanner;

/**
 * Represents a simple chatbot, EDITH, that manages tasks.
 */
public class ChatBot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    private final Scanner in;

    /**
     * Constructs a EDITH.ChatBot object with the specified file path for task storage.
     *
     * @param filePath The file path for task storage.
     */
    public ChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.loadTasksFromFile());
        parser = new Parser();
        ui.printWelcomeMessage();
        in = new Scanner(System.in);
    }

    /**
     * Runs EDITH.
     */
    public void run() {
        while (true) {
            String command = in.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                saveTasksToFile();
                break;
            }
            parser.processCommand(command, tasks, storage, ui);
        }
    }

    private void saveTasksToFile() {
        storage.saveTasksToFile(tasks, ui);
    }

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot("text.txt");
        chatBot.run();
    }
}
