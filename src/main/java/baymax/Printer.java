package baymax;

import exceptions.*;

import java.util.ArrayList;

/**
 * Handles the printing of the replies to the user
 */


public class Printer {

    private static final String BOT_NAME = "helper";
    private static final String INDENT = "     ";
    private static final String LINE = "~------------------------------------------------------------~";
    private static final String TAGLINE = "Bala-lalala...";
    private static final String ERROR_TAGLINE = "Ohh noo...";
    private static final int LAST_INDEX = 1;
    public static final String ERROR_MSG = "ERROR: ";


    public static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println(INDENT + "Hello! I'm " + BOT_NAME + ", your personal task manager." + System.lineSeparator()
                + INDENT + "What can I do for you today? :)");
        System.out.println(LINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println(INDENT + "I hope you are satisfied with my service! Goodbye. :)");
        System.out.println(LINE);
    }

    public static void printTaskCount(ArrayList<Task> tasks) {
        System.out.println(INDENT + "You have a total of " + tasks.size() + " tasks now. :)");
    }

    public static void printAddedTask(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've added this task:");
        System.out.println(INDENT + tasks.get(tasks.size() - LAST_INDEX).toAddString());
        printTaskCount(tasks);
        System.out.println(LINE);
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        if (tasks.isEmpty()) {
            System.out.println(INDENT + ERROR_TAGLINE + " You have no tasks yet. Try adding some!");
        } else {
            System.out.println(INDENT + TAGLINE + " Displaying your tasks: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(INDENT + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(INDENT + "You have a total of " + tasks.size() + " tasks now. :)");
        }
        System.out.println(LINE);
    }

    public static void printMark(Task task) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've marked this task:");
        System.out.println(INDENT + task);
        System.out.println(LINE);
    }

    public static void printUnmark(Task task) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've unmarked this task:");
        System.out.println(INDENT + task);
        System.out.println(LINE);
    }

    public static void printDelete(ArrayList<Task> tasks, int index) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've deleted this task:");
        System.out.println(INDENT + tasks.get(index));
        System.out.println(LINE);

    }

    public static void printFindList(ArrayList<Task> findArrayList) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " Here are the tasks that contains your keyword:");
        for (int i = 0; i < findArrayList.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + findArrayList.get(i));
        }
        System.out.println(LINE);
    }

    public static void printUnknownInput() {
        System.out.println(LINE);
        System.out.println(INDENT + ERROR_TAGLINE + "I don't know what that means. :(");
        System.out.println(LINE);
    }

    public static void handleInvalidTodoSyntaxException (InvalidTodoSyntaxException e) {
        System.out.println(LINE);
        System.out.println(ERROR_MSG + e.getMessage());
        System.out.println(LINE);
    }

    public static void handleInvalidDeadlineSyntaxException (InvalidDeadlineSyntaxException e) {
        System.out.println(LINE);
        System.out.println(ERROR_MSG + e.getMessage());
        System.out.println(LINE);
    }

    public static void handleInvalidEventSyntaxException (InvalidEventSyntaxException e) {
        System.out.println(LINE);
        System.out.println(ERROR_MSG + e.getMessage());
        System.out.println(LINE);
    }

    public static void handleInvalidFindTaskException (InvalidFindTaskException e) {
        System.out.println(LINE);
        System.out.println(ERROR_MSG + e.getMessage());
        System.out.println(LINE);
    }

    public static void handleInvalidLoadTaskException (InvalidLoadTaskException e) {
        System.out.println(LINE);
        System.out.println(ERROR_MSG + e.getMessage());
        System.out.println(LINE);
    }

}
