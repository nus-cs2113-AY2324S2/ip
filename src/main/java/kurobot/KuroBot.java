package kurobot;

import kurobot.exceptions.InvalidCommandException;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

/**
 * A chatbot that can store tasks and perform various operations on the tasks.
 */
public class KuroBot {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storageFile;
    private static TaskList amendTasks;
    private static Ui ui = new Ui();
    private static int taskNum = 0;
    private static boolean isStart;
    private static Scanner scanner;

    private static void printTasks() {
        ui.printTasks(tasks);
    }

    private static void start() {
        ui.showWelcomeMessage();
        isStart = true;
        scanner = new Scanner(System.in);
    }

    private static void end() {
        ui.showGoodByeMessage();
        isStart = false;
        scanner.close();
    }

    /**
     * Extracts the command keyword and perform operations corresponding
     * to the command keyword.
     *
     * @param input Input entered by the user.
     * @throws InvalidCommandException If no recognised command keyword was entered.
     */
    private static void manageTasks(String input) throws InvalidCommandException {
        Parser parserInput= new Parser(input);
        String command = parserInput.parserCommand();
        amendTasks = new TaskList(tasks, taskNum, input);
        switch (command) {
        case "bye":
            end();
            break;
        case "list":
            printTasks();
            break;
        case "mark":
            tasks = amendTasks.markTask(true);
            break;
        case "unmark":
            tasks = amendTasks.markTask(false);
            break;
        case "todo":
            tasks = amendTasks.addTodo();
            taskNum = amendTasks.getTaskNum();
            break;
        case "deadline":
            tasks = amendTasks.addDeadline();
            taskNum = amendTasks.getTaskNum();
            break;
        case "event":
            tasks = amendTasks.addEvent();
            taskNum = amendTasks.getTaskNum();
            break;
        case "delete":
            tasks = amendTasks.deleteTask();
            taskNum = amendTasks.getTaskNum();
            break;
        case "find":
            amendTasks.findTask();
            break;
        default:
            throw new InvalidCommandException();
        }
        storeNewData();
    }

    public static void main(String[] args) {
        //display welcome message
        start();
        processOldData();
        printTasks();
        executeCommand();
    }

    private static void processOldData() {
        try {
            storageFile = new Storage();
            tasks = storageFile.readFileContents();
            taskNum = storageFile.getTaskNum();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void executeCommand() {
        while (isStart) {
            String input = scanner.nextLine();
            try {
                manageTasks(input);
            } catch (InvalidCommandException e) {
                ui.showInvalidCommand();
            }
        }
    }

    private static void storeNewData() {
        try {
            storageFile.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
