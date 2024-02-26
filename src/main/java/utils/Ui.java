package utils;

public class Ui {
    public static void printWelcomeMessage() {
        System.out.println(constants.BREAKLINE);
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println(constants.BREAKLINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(constants.BREAKLINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(constants.BREAKLINE);
    }

    public static void printTaskAddedMessage(int taskCount, String task) {
        System.out.println(constants.BREAKLINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " task(s) in the list.");
        System.out.println(constants.BREAKLINE);
    }

    public static void printTaskDeletedMessage(int taskCount, String task) {
        System.out.println(constants.BREAKLINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " task(s) in the list.");
        System.out.println(constants.BREAKLINE);
    }

    public static void printError(String message) {
        System.out.println(constants.BREAKLINE);
        System.out.println(message);
        System.out.println(constants.BREAKLINE);
    }
}
