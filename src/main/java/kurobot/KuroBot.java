package kurobot;

import kurobot.exceptions.InvalidCommandException;
import kurobot.exceptions.InvalidDescriptionException;
import kurobot.exceptions.InvalidTimeException;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class KuroBot {

    //private static Task[] tasks = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storageFile;
    private static TaskList amendTasks;
    private static int taskNum = 0;
    private static boolean isStart;
    private static final int LINE_LEN = 60;
    private static final String LINE =  "-".repeat(LINE_LEN);
    private static Scanner scanner;

    private static final String LOGO =
            " ___   ___    ___    ___ \n"
                    + "|   |/   /   |  |   |  | \n"
                    + "|       /    |  |   |  | \n"
                    + "|   |\\   \\   |_ |___| _| \n"
                    + "|___| \\___\\    |_____|   \n";


    private static void printTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks){
            System.out.println(tasks.indexOf(task)+1 + "." + task.printTask());
        }
        System.out.println(LINE);
    }

    private static void start() {
        System.out.println(LINE);
        System.out.println("Hello! I'm KuroBot\n" + "What can I do for you?");
        System.out.println(LINE);
        isStart = true;
        scanner = new Scanner(System.in);
    }

    private static void end() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println(LOGO);
        isStart = false;
        scanner.close();
    }

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
        default:
            throw new InvalidCommandException();
        }
        try {
            storageFile.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        //display welcome message
        start();

        try {
            storageFile = new Storage();
            tasks = storageFile.readFileContents();
            taskNum = storageFile.getTaskNum();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        printTasks();

        while (isStart) {
            String input = scanner.nextLine();
            try {
                manageTasks(input);
            } catch (InvalidCommandException e) {
                System.out.println(LINE);
                System.out.println("Whoops! Please enter a valid command~");
                System.out.println(LINE);
            }
        }
    }

}
