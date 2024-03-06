package fredbot;

import fredbot.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String MESSAGE_ADD = "Are you sure you'll ever get to it? Fine, I've added this task:";
    private static final String MESSAGE_DELETE = "Finally lifting my load? I've removed this task:";
    private static final String MESSAGE_EMPTY_LIST = "Go and touch some grass... your list is empty.";
    private static final String MESSAGE_EMPTY_DESCRIPTION = "I can't do that if you don't give me the description...";
    private static final String MESSAGE_ERROR = "What did you do... Something went wrong.";
    private static final String MESSAGE_UNKNOWN_COMMAND = "I have no idea what you just said.";
    private static final String MESSAGE_GOODBYE = "Finally... goodbye.";
    private static final String MESSAGE_LIST_HEADER = "Mr Busy over here has these tasks in his list:";
    private static final String MESSAGE_MARK = "Ok and do you want a medal for that? I've marked this as done:";
    private static final String MESSAGE_MATCHES_HEADER = "Are these what you're looking for? I found these:";
    private static final String MESSAGE_NO_MATCHES = "I don't know what to tell you... There are no such tasks.";
    private static final String MESSAGE_UNMARK = "Why am I not surprised... I've marked this task as not done yet:";
    private static final String MESSAGE_WELCOME = "Yo I'm FredBot. What do you want with me?";

    private static final Scanner SCANNER = new Scanner(System.in);

    public Ui() {

    }

    public static void showWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    public static String readUserInput() {
        return SCANNER.nextLine();
    }

    public static void showEmptyDescription() {
        System.out.println(MESSAGE_EMPTY_DESCRIPTION);
    }

    public static void showErrorMessage() {
        System.out.println(MESSAGE_ERROR);
    }

    public static void showUnknownCommandMessage() {
        System.out.println(MESSAGE_UNKNOWN_COMMAND);
    }

    public static void showMessageAdd(TaskList tasks) {
        System.out.println(MESSAGE_ADD);
        System.out.println(tasks.getTask(tasks.getCount()).toString());
    }

    public static void showNumberOfTasks(int count, String task) {
        System.out.println("You now have " + count + task + "in the list.");
    }

    public static void showEmptyListMessage() {
        System.out.println(MESSAGE_EMPTY_LIST);
    }

    public static void showList(ArrayList<Task> allTasks, int count) {
        System.out.println(MESSAGE_LIST_HEADER);
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + allTasks.get(i).toString());
        }
    }

    public static void showMatches(ArrayList<Task> matches) {
        System.out.println(MESSAGE_MATCHES_HEADER);
        int i = 1;
        for (Task t : matches) {
            System.out.println((i) + ". " + t.toString());
            i++;
        }
    }

    public static void showGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }

    public static void showDeleteMessage(ArrayList<Task> allTasks, int index) {
        System.out.println(MESSAGE_DELETE);
        System.out.println(allTasks.remove(index).toString());
    }

    public static void showUnmarkMessage(ArrayList<Task> allTasks, int index) {
        System.out.println(MESSAGE_UNMARK);
        System.out.println(allTasks.get(index).toString());
    }

    public static void showMarkMessage(ArrayList<Task> allTasks, int index) {
        System.out.println(MESSAGE_MARK);
        System.out.println(allTasks.get(index).toString());
    }

    public static void showNoMatches() {
        System.out.println(MESSAGE_NO_MATCHES);
    }
}
