package natsu.util;

import static natsu.util.TaskList.list;

/**
 * Provides user interface functionalities for displaying messages to the user.
 * This class includes methods to print various types of messages, such as welcome and exit messages,
 * task addition, completion, deletion messages, and the list of tasks.
 */
public class Ui {

    /**
     * Prints a line separator to the console.
     */
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the welcome message to the user.
     */
    public static void printWelcomeMessage() {
        printLine();
        System.out.println("     Hello! I'm Natsu");
        System.out.println("     What can I do for you?");
        printLine();
    }

    /**
     * Prints the exit message to the user.
     */
    public static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints a message indicating a task has been added, including the task's description
     * and the current total number of tasks.
     *
     * @param task The task that was added.
     */
    public static void printTaskAdded(String task) {
        printLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void printTaskDone(String task) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        printLine();
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public static void printTaskUndone(String task) {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        printLine();
    }

    /**
     * Prints the list of all tasks currently in the task list.
     */
    public static void printList() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "." + list.get(i).toString());
        }
        printLine();
    }

    /**
     * Prints a message indicating a task has been deleted, including the task's description
     * and the current total number of tasks after deletion.
     *
     * @param task The task that was deleted.
     * @param size The new total number of tasks after the deletion.
     */
    public static void printTaskDeleted(String task, int size) {
        printLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        printLine();
    }
}
