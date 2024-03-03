package chatbot;

import chatbot.commands.ByeCommand;
import chatbot.commands.Command;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the chatbot.
 */
public class Chatbot {
    private TaskList taskList;
    private boolean isExit = false;
    Scanner in = new Scanner(System.in);
    public Chatbot() {
    }

    /**
     * Initiates the chatbot, causing it to print a greeting and
     * read existing tasks from a text file.
     */
    public void initiate() throws IOException {
        UI.printGreeting();
        Storage storage = new Storage();
        try {
            taskList = storage.readFile();
        } catch (ChatbotException e) {
            e.printDescription();
        } catch (FileNotFoundException e) {
            Storage.createFile();
            try {
                taskList = storage.readFile();
            } catch (ChatbotException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (taskList.getListLength() > 0) {
            UI.printFileExits();
        }
    }

    /**
     * Exits the chatbot and saves the tasks to a text file.
     *
     * @throws IOException If the text file is invalid.
     */
    public void exit() throws IOException {
        UI.printBye();
        Storage.writeFile(taskList);
    }

    /**
     * Runs the chatbot. Reads and executes user commands.
     */
    public void run() {
        do {
            try {
                String input = in.nextLine();
                Command command = Parser.readCommand(taskList, input);
                command.execute(false);
                if (command instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (ChatbotException e) {
                e.printDescription();
            }
        } while (!isExit);
    }
}
