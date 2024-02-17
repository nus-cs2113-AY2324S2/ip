package kobot;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Kobot";
    private static final String LOGO = "#########################################\n"
            + "##     _   __      _           _       ##\n"
            + "##    | | / /     | |         | |      ##\n"
            + "##    | |/ /  ___ | |__   ___ | |_     ##\n"
            + "##    |    \\ / _ \\| '_ \\ / _ \\| __|    ##\n"
            + "##    | |\\  \\ (_) | |_) | (_) | |_     ##\n"
            + "##    \\_| \\_/\\___/|_.__/ \\___/ \\__|    ##\n"
            + "##                                     ##\n"
            + "#########################################\n";

    private static final String LINE_DIVIDER = "-------------------------------------------";

    public static void printLineDivider() {
        System.out.println(LINE_DIVIDER);
    }
    public static void printHelloMessage() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm " + BOT_NAME + ". How may I assist you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye, hope to see you again!");
    }
    
    public static String receiveInput() throws NoSuchElementException {
        printLineDivider();
        System.out.print("> ");
        String input = "";
        
        try {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
        } catch (NoSuchElementException exception) {
            System.exit(0);
        }
        
        printLineDivider();
        return input;
    }

    public static void printEmptyArgumentErrorMessage() {
        System.out.println("Empty or whitespace-only fields are not allowed.");
    }

    public static void printMissingArgumentErrorMessage() {
        System.out.println("Missing information. Please try again.");
    }
    
    public static void printIndexOutOfBoundsMessage(String action) {
        System.out.println("Invalid index. Failed to " + action + " item.");
    }

    public static void printToDoCommandUsage() {
        System.out.println("Command to add a new to-do task:");
        System.out.println("todo <description>");
    }

    public static void printDeadlineCommandUsage() {
        System.out.println("Command to add a new deadline:");
        System.out.println("deadline <description> /by <datetime>");
    }

    public static void printEventCommandUsage() {
        System.out.println("Command to add a new event:");
        System.out.println("event <description> /from <datetime> /to <datetime>");
    }
    
    public static void printMarkCommandUsage() {
        System.out.println("Command to mark task as completed:");
        System.out.println("mark <task index>");
    }

    public static void printUnmarkCommandUsage() {
        System.out.println("Command to mark task as not completed:");
        System.out.println("unmark <task index>");
    }

    public static void printDeleteCommandUsage() {
        System.out.println("Command to delete task:");
        System.out.println("delete <task index>");
    }
}