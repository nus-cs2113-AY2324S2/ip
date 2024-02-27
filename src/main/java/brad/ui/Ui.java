package brad.ui;

import brad.parser.Command;
import brad.tasks.TaskList;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "__________________________________________________________";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String BYE = "bye";

    private final PrintStream out;
    private final Scanner in;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void greetUser() {
        final String name = "brad";
        System.out.println("Hello I am " + name + ".\n");
        System.out.println("How can I help you today?\n");
    }

    public String getUserInput() {
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }
    public void printOutput(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public void printFileNotFound() {
        printOutput("File is not found. Make sure that the " +
                "data file is located in: 'data/data.md'\n" +
                "I'm unable to save.");
    }

    public void printDataCorrupted() {
        printOutput("File is corrupted! Please check the file.\n" +
                "Exiting program...\n");
    }

    public void printError(String message) {
        printOutput(SEPARATOR);
        printOutput(message);
        printOutput(SEPARATOR);
    }

    public void printMissingArgument(Command command) {
        switch (command) {
            case MARK:
                printOutput("Uh oh. Please enter a number to mark the corresponding " +
                        "task as done");
                break;
            case UNMARK:
                printOutput("Uh oh. Please enter a number to mark the corresponding " +
                        "task as undone");
                break;
            case DEADLINE:
                printOutput("Hey, you can't give me an empty deadline with no " +
                        "specified due date!");
                break;
            case EVENT:
                printOutput("Hey, you can't give me an event with no start & end time!");
                break;
            case TODO:
                printOutput("Hey, you can't give me an empty todo!");
                break;
            case DELETE:
                printOutput("Uh oh. Please enter a number to delete this task");
                break;
            default:
                printOutput("Please enter something valid");
                break;
        }
    }

    public void printUnknownCommand() {
        printOutput("Huh?! Sorry I don't understand. T_T\n Please only enter valid commands: " +
        "'list', 'mark', 'unmark', 'todo', 'deadline', 'event', 'delete', 'bye'");
    }

    public void printInvalidNumber(int listSize) {
        printOutput("No! >:( Exceeded existing list size of: " + listSize +
                                "\nPlease enter a valid number.\n");
    }
    public void printAllTasks(TaskList tasklist) {
        printOutput(tasklist.getList());
    }

    public void showResult(String task, Command command, int size) {
        switch (command) {
            case TODO:
                printAddActionResult(task, size, TODO);
                break;
            case EVENT:
                printAddActionResult(task, size, EVENT);
                break;
            case DEADLINE:
                printAddActionResult(task, size, DEADLINE);
                break;
            case MARK:
                printMarkActionResult(task);
                break;
            case UNMARK:
                printUnmarkActionResult(task);
                break;
            case DELETE:
                printDeleteActionResult(task, size);
                break;
        }
    }

    public void printAddActionResult(String task, int size, String command)
    {
        String message = "Got it. I've added a \n" + command  + ":\n" + task
            + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }

    public void printMarkActionResult(String task) {
        String message = "Nice! I've marked this task as done:\n" + task;
        printOutput(message);
    }

    public void printUnmarkActionResult(String task) {
        String message = "Nice! I've marked this task as not done:\n" + task;
        printOutput(message);
    }

    public void printDeleteActionResult(String task, int size) {
        String message = "Got it. I've removed this task:\n" + task
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }
}