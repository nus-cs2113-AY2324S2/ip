package fredbot;

import fredbot.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
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
    private static final String MESSAGE_UNMARK = "Why am I not surprised... I've marked this task as not done yet:";
    private static final String MESSAGE_WELCOME = "Yo I'm FredBot. What do you want with me?";

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Constructs the Ui object.
     */
    public Ui() {

    }

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    /**
     * Reads the line input by the user.
     *
     * @return User input.
     */
    public String readUserInput() {
        return SCANNER.nextLine();
    }

    /**
     * Prints an empty description error message.
     */
    public void showEmptyDescription() {
        System.out.println(MESSAGE_EMPTY_DESCRIPTION);
    }

    /**
     * Prints a generic error message.
     */
    public void showErrorMessage() {
        System.out.println(MESSAGE_ERROR);
    }

    /**
     * Prints an unknown command error message.
     */
    public void showUnknownCommandMessage() {
        System.out.println(MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Prints a task added message as well as the task that was just added.
     */
    public void showMessageAdd(TaskList tasks) {
        System.out.println(MESSAGE_ADD);
        System.out.println(tasks.getTask(tasks.getCount()).toString());
    }

    /**
     * Tells the user the number of tasks in the task list.
     */
    public void showNumberOfTasks(int count, String task) {
        System.out.println("You now have " + count + task + "in the list.");
    }

    /**
     * Prints an empty list error message.
     */
    public void showEmptyListMessage() {
        System.out.println(MESSAGE_EMPTY_LIST);
    }

    /**
     * Prints the task list for the user.
     */
    public void showList(ArrayList<Task> allTasks, int count) {
        System.out.println(MESSAGE_LIST_HEADER);
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + allTasks.get(i).toString());
        }
    }

    /**
     * Prints a exit message.
     */
    public void showGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }

    /**
     * Prints a task deleted message as well as the task that was just deleted.
     */
    public void showDeleteMessage(ArrayList<Task> allTasks, int index) {
        System.out.println(MESSAGE_DELETE);
        System.out.println(allTasks.remove(index).toString());
    }

    /**
     * Prints a task unmarked message.
     */
    public void showUnmarkMessage(ArrayList<Task> allTasks, int index) {
        System.out.println(MESSAGE_UNMARK);
        System.out.println(allTasks.get(index).toString());
    }

    /**
     * Prints a task marked message.
     */
    public void showMarkMessage(ArrayList<Task> allTasks, int index) {
        System.out.println(MESSAGE_MARK);
        System.out.println(allTasks.get(index).toString());
    }
}
