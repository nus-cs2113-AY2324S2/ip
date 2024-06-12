package util;

import tasks.Task;

/**
 * The class that interacts with the user. General messages are placed here.
 */
public class Ui {

    /**
     * Prints on initialisation.
     */
    public static void greetingMessage() {
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");
    }

    /**
     * Prints on exit.
     */
    public static void farewellMessage() {
        System.out.println("Farewell. And may the fortunes be ever in your favour.");
    }

    /**
     * Prints to let the user know that they are accessing and index that is out of
     * bounds, through the mark, unmark or delete methods.
     */
    public static void indexOutOfBoundsMessage() {
        System.out.println("You are trying to access forbidden territory. Tread carefully.");
    }

    /**
     * Prints a message to let the user know that their list is empty.
     */
    public static void emptyListMessage() {
        System.out.println("Your list is empty. Try again, when you have become more productive.");
    }

    /**
     * Prints to let the user know that they are missing / in the deadline and
     * event inputs.
     */
    public static void missingSlashMessage() {
        System.out.println("You are missing a /. Do not let this happen again.");
    }

    /**
     * Prints out a task with indentation.
     *
     * @param task: a singular task that can be printed, T, D or E.
     */
    public static void printTask(Task task) {
        System.out.println("  " + task);
    }
}
