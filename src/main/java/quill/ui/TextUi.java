package quill.ui;

import quill.task.Task;
import quill.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TextUi Class is responsible for user interaction,
 * including reading user input and displaying messages.
 */
public class TextUi {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String NAME = "Quill";
    private final Scanner in;

    /**
     * Constructs a TextUi object and initializes the user input scanner.
     */
    public TextUi() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the command from the user and returns it as a string.
     *
     * @return The user's input command as a string.
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public static void showWelcomeMessage() {
        System.out.println(DIVIDER + "\nHello! I'm " + NAME + ".");
        System.out.println("What can i do for you?\n" + DIVIDER);
    }

    /**
     * Displays the message for adding tasks to the task list.
     *
     * @param tasks The task being added to the task list.
     */
    public static void showAddTask(Task tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + TaskList.getTotalTasks() + " tasks in the list.");
    }

    /**
     * Displays the message for deleting tasks from the task list
     *
     * @param tasks The task to be deleted from the task list.
     */
    public static void showDeleteTask(Task tasks) {
        System.out.println("Got it. I've removed this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + (TaskList.getTotalTasks() - 1) + " tasks in the list.");
    }

    /**
     * Displays a divider line to separate different section of output.
     */
    public static void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays the goodbye message when exiting the application.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public static void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Zero tasks. Add something already.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.getTotalTasks(); i++) {
                System.out.println(i + 1 + "." + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Displays the list of matching tasks in the task list.
     *
     * @param tasks The list of matching tasks to be displayed.
     */
    public static void showFindList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Nothing. Tough luck.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        }
    }

    public static void showUnknownMessage() {
        System.out.println("Enough with the gibberish. Stick to the commands I understand:");
        System.out.println("bye, list, todo, deadline, event, mark, unmark, delete, find. Got it? Next!");
    }
}
