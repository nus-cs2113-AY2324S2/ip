package chatbot.ui;

import chatbot.tasks.Task;

/**
 * Represents the user interface for the chatbot.
 * Contains the messages to be printed.
 */
public class UI {
    public static void printSeparator() {
        System.out.println("------------------------------");
    }
    public static void printGreeting() {
        System.out.println("Hello. You may call me Horizon. \n" +
                "Looks like you need me to remember things for you. Again. ");
        printSeparator();
    }
    public static void printFileExits() {
        System.out.println("You already have tasks. Type 'list' to take a look? ");
        printSeparator();
    }
    public static void printBye() {
        System.out.println("Bye. If I save your stuff, will you leave for good? ");
    }
    public static void printHelp() {
        System.out.println("Want to know what I can do? \n" +
                "I can add 'todo', 'deadline', and 'event' tasks. \n" +
                "Or 'list', 'mark', 'unmark', and 'delete' tasks. \n" +
                "Use 'find' to look for a specific task. \n" +
                "Also, type 'bye' to exit. I suggest this one. ");
        printSeparator();
    }
    public static void printData(Task task) {
        System.out.println(task.getData());
    }
    public static void printAddTaskReply() {
        System.out.println("Got it. Don't worry, I won't forget. ");
    }
    public static void printMarkReply() {
        System.out.println("Not as incompetent as I thought. Marked. ");
    }
    public static void printUnmarkReply() {
        System.out.println("Failing to meet expectations, are we? Unmarked. ");
    }
    public static void printDeleteReply() {
        System.out.println("Deleted. ");
    }
    public static void printFindReply() {
        System.out.println("Here they are: ");
    }
    public static void printNoMatch() {
        System.out.println("Wait, there are no matches. ");
    }
}
