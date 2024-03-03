package gandalf;

import action.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    // Scanner object
    static Scanner in = new Scanner(System.in);

    static ArrayList<Task> listTasks = new ArrayList<>();

    public static final String LINE = "____________________________________________________________";
    public static final String BYE_STATEMENT = "bye";

    private static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("What would you like to be added to the list?");
        System.out.println(LINE);
    }

    public static boolean hasSaidList(String userInput) {
        return userInput.equals("list");
    }

    public static boolean hasSaidBye(String text) {
        return text.equals(BYE_STATEMENT);
    }

    public static String getUserInput() {
        return in.nextLine();
    }

    public static void startMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Gandalf, your favorite personal assistant.");
        System.out.println("Please wait while I load your previous To-Do List.");
    }

    public static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        in.close();
    }

    public static void startProgram() {
        Storage.loadData(listTasks);
        printWelcomeMessage();

        while (true) {
            String userInput = getUserInput();
            if (hasSaidBye(userInput)) {
                System.out.println(LINE);
                Storage.saveTasks(listTasks);
                return;
            } else if (hasSaidList(userInput)) {
                TaskList.printList(listTasks);
            } else {
                TaskList.handleUserTasks(userInput, listTasks);
            }
        }
    }
    public static void printLoadingMessage () {
        System.out.println(LINE);
        System.out.println("Loading previous To-Do List....");
    }

    public static void printFileNotFoundMessage() {
        System.out.println(LINE);
        System.out.println("File not found. You may start creating your new list.");
    }
    public static void printEmptyFileMessage() {
        System.out.println(LINE);
        System.out.println("No save data found. You may start creating your new list.");
    }
    public static void printListIsFullMessage() {
        System.out.println(LINE);
        System.out.println("List is full. Cannot add more items.");
        System.out.println(LINE);
    }

    public static void printInvalidKeywordMessage() {
        System.out.println(LINE);
        System.out.println("Invalid keyword, the available keywords are:"
                + "\n(todo), (deadline), (event)"
                + "\nPlease try again.");
        System.out.println(LINE);
    }

    public static void printMissingDescriptionMessage() {
        System.out.println(LINE);
        System.out.println("Please fill in the description of the task.");
        System.out.println(LINE);
    }

    public static void printInvalidTaskIndexMessage() {
        System.out.println(LINE);
        System.out.println("Invalid task index. Please try again.");
        System.out.println(LINE);
    }

    public static void printInvalidTaskDeletionMessage() {
        System.out.println(LINE);
        System.out.println("Deletion unsuccessful, please try again.");
        System.out.println(LINE);
    }
}
