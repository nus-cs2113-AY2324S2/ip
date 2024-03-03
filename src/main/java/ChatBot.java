import edithExceptionPackage.ChatBotExceptions;
import parserPackage.Parser;
import storagePackage.Storage;
import taskListPackage.TaskList;
import ui.Ui;

import java.util.Scanner;

public class ChatBot {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    private final Scanner in;

    public ChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.loadTasksFromFile());
        parser = new Parser();
        ui.printWelcomeMessage();
        in = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            String command = in.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                ui.printFormattedMessage("Bye. Hope to see you again soon!");
                saveTasksToFile();
                break;
            }
            parser.processCommand(command, tasks, storage, ui);
        }
    }

    private void saveTasksToFile() {

        storage.saveTasksToFile(tasks);
    }

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot("text.txt");
        chatBot.run();
    }
}
