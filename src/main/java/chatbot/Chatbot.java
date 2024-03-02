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
    public void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(".\\data\\chatbot.txt");
        for (int i = 0; i < taskList.getListLength(); i += 1) {
            Task task = taskList.get(i);
            fileWriter.write(task.isDone() + "@" + task.getTaskName() + "@" + task.getCommand() + "\n");
        }
        fileWriter.close();
    }
    public void exit() throws IOException {
        UI.printBye();
        writeToFile();
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
