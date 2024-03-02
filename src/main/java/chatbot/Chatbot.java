package chatbot;

import chatbot.commands.ByeCommand;
import chatbot.commands.Command;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Chatbot {
    private TaskList taskList;
    private boolean isExit = false;
    Scanner in = new Scanner(System.in);
    public Chatbot() {
    }
    public void initiate() {
        UI.printGreeting();
        Storage storage = new Storage();
        try {
            taskList = storage.readFile();
        } catch (ChatbotException e) {
            e.printDescription();
        } catch (FileNotFoundException e) {
            System.out.println("No file found. ");
        }
        if (taskList.getListLength() > 0) {
            UI.printFileExits();
        }
    }
    public void exit() throws IOException {
        UI.printBye();
        Storage.writeFile(taskList);
    }
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
