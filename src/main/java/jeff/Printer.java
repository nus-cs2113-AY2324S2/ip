package jeff;

import java.util.ArrayList;

/**
 * Provides static methods for printing messages to the standard output.
 */
public class Printer {
    private static final String INDENT = "     ";
    private static final String DIVIDER = "    ____________________________________________________________";

    /**
     * Prints a string with an indentation to the standard output.
     *
     * @param s The string to be printed with indentation.
     */
    public static void printIndent(String s) {
        System.out.println(INDENT + s);
    }

    /**
     * Prints a divider to the standard output.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a welcome message to greet the user.
     */
    public static void printWelcome() {
        printDivider();
        printIndent("Hello! I'm Jeff");
        printIndent("What can I do for you?");
        printDivider();
    }

    /**
     * Prints a farewell message indicating the end of the program.
     */
    public static void printBye() {
        printIndent("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the tasks in TaskList.
     */
    public static void printTasks() {
        printIndent("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.size(); i++) {
            printIndent((i + 1) + "." + TaskList.get(i));
        }
    }

    /**
     * Prints a confirmation message after adding a task.
     * Informs the user about the updated total number of tasks in the list.
     */
    public static void printMatchingTasks(ArrayList<Task> matchingTasks) {
        printIndent("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            printIndent((i + 1) + "." + matchingTasks.get(i));
        }
    }

    public static void printNoMatchingTasks() {
        printIndent("No matching tasks found.");
    }

    public static void printAddTask() {
        printIndent("Got it. I've added this task:");
        printIndent("  " + TaskList.back());
        printIndent("Now you have " + TaskList.size() + " tasks in the list.");
    }

    /**
     * Prints a confirmation message after deleting a task.
     * Informs the user about the updated total number of tasks in the list.
     */
    public static void printDeleteTask(Task deletedTask) {
        printIndent("Noted. I've removed this task:");
        printIndent("  " + deletedTask);
        printIndent("Now you have " + TaskList.size() + " tasks in the list.");
    }

    /**
     * Prints a confirmation message after marking a task.
     *
     * @param currentTask The task that has been marked.
     */
    public static void printMarkTask(Task currentTask) {
        printIndent("Nice! I've marked this task as done:");
        printIndent("  " + currentTask);
    }

    /**
     * Prints a confirmation message after unmarking a task.
     *
     * @param currentTask The task that has been unmarked.
     */
    public static void printUnmarkTask(Task currentTask) {
        printIndent("OK, I've marked this task as not done yet:");
        printIndent("  " + currentTask);
    }

    /**
     * Prints a message indicating the inability to mark tasks due to an empty task list.
     */
    public static void printUnableToMark() {
        printIndent("Unable to mark any tasks as task list is empty.");
    }

    /**
     * Prints a message indicating the inability to unmark tasks due to an empty task list.
     */
    public static void printUnableToUnmark() {
        printIndent("Unable to unmark any tasks as task list is empty.");
    }

    /**
     * Prints a message indicating the inability to delete tasks due to an empty task list.
     */
    public static void printUnableToDelete() {
        printIndent("Unable to delete any tasks as task list is empty.");
    }
}
