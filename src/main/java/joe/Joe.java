package joe;

import joe.command.Command;
import joe.util.FileManager;
import joe.util.InputParser;
import joe.util.Printer;
import joe.task.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class Joe {
    protected static TaskManager taskManager;

    public static void main(String[] args) {
        Printer.printGreeting();
        runJoe();
        Printer.printExitMessage();
    }

    private static void runJoe() {
        Scanner in = new Scanner(System.in);
        taskManager = new TaskManager();

        try {
            FileManager.loadData(taskManager);
        } catch (IOException e) {
            Printer.printLoadError();
        }

        Command command;
        do {
            String input = in.nextLine();
            input = input.trim();
            command = InputParser.getCommand(input);
            command.executeCommand(taskManager);
        } while (!command.isExit());

        in.close();
    }

}
