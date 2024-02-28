package carrot.ui;

import carrot.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String logo =
            "   _____                     _   \n" +
            "  / ____|                   | |  \n" +
            " | |     __ _ _ __ _ __ ___ | |_ \n" +
            " | |    / _` | '__| '__/ _ \\| __|\n" +
            " | |___| (_| | |  | | | (_) | |_ \n" +
            "  \\_____\\__,_|_|  |_|  \\___/ \\__|\n";

    private static final String MESSAGE_DIVIDER = "-------------------------------------";
    private static final String COMMAND_LIST =
            "Available Commands:\n" +
                    "1. todo <taskdescription> - Add a new todo task\n" +
                    "2. deadline <taskdescription> /<by> - Add a new task with a deadline <by>\n" +
                    "3. event <taskdescription> /<from> /<to> - Add a new task that starts <from> and ends <to>\n" +
                    "4. mark <taskindex> - Mark a task as done\n" +
                    "5. unmark <taskindex> - Mark a task as not done\n" +
                    "6. delete <taskindex> - delete a task from the list\n" +
                    "7. list - List all tasks recorded\n" +
                    "8. bye - Exit the program\n" +
                    "9. help - Show available commands";

    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void greetUser() {
        System.out.println("Hello from\n" + logo);
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Hello! I'm Carrot!");
        System.out.println("What can I do for you?");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static String readUserInput() {
        return scanner.nextLine().trim();
    }

    public static void printHelpCommand() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println(COMMAND_LIST);
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printInvalidCommand() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Invalid command");
        System.out.println("Type \"help\" to view available commands and syntax");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printInvalidTaskIndexError() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("ERROR: Invalid task index. " +
                "Type \"list\" to see available task indices\n" +
                "Usage: \"delete/mark/unmark <taskIndex>\"");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printChangedTaskStatus(boolean isDone, Task task) {
        System.out.println(MESSAGE_DIVIDER);
        if (isDone == true) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("\t" + task);
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printAddedTask(Task task, ArrayList<Task> listOfTasks) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d task(s) in the list.%n", listOfTasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printDeletedTask(Task task, ArrayList<Task> listOfTasks) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d task(s) in the list.%n", listOfTasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printListItems(ArrayList<Task> listOfTasks) {
        int numberOfTasks = listOfTasks.size();

        System.out.println(MESSAGE_DIVIDER);
        if (numberOfTasks == 0) {
            System.out.println("No tasks added");
        } else {
            System.out.println("Here are the task(s) in your list:");
            for (int i = 1; i <= numberOfTasks; i++) {
                System.out.println(i + "." + listOfTasks.get(i - 1));
            }
        }
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printScannerInputError() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Failed to read input. Exiting Program...");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void sayGoodbye() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(MESSAGE_DIVIDER);
    }
}
