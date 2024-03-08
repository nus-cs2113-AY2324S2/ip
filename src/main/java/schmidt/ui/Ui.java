package schmidt.ui;

import schmidt.task.Task;
import schmidt.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private static final String LINE = "------------------------------------------------------------";
    private static final String LOGO = "░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗\n" +
            "██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝\n" +
            "╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░\n" +
            "░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░\n" +
            "██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░\n" +
            "╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░";
    private static final String HELP_MESSAGE = "I'm sorry, but the valid commands are:\n" +
            "\tbye\n" +
            "\tlist\n" +
            "\ttodo <description>\n" +
            "\tdeadline <description> /by <date>\n" +
            "\tevent <description> /from <date> /to <date>\n" +
            "\tmark <task number>\n" +
            "\tunmark <task number>\n" +
            "\tdelete <task number>";
    private final Scanner scanner;

    /**
     * Constructs a user interface.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a message with lines above and below it for better user readability.
     *
     * @param message the message to print
     */
    public void printWithLines(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints a message to the user.
     *
     * @param message the message to print
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command from the user.
     *
     * @return the command read from the user
     */
    public String readCommand() {
        System.out.print("\t-> ");
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcome() {
        printMessage(LOGO);
        printWithLines("Hello! I'm Schmidt\nWhat can I do for you?");
    }

    /**
     * Prints the goodbye message to the user.
     */
    public void printGoodbye() {
        printWithLines("Goodbye! Hope to see you again soon!");
    }

    /**
     * Prints an error message to the user.
     *
     * @param errorMessage the error message to print
     */
    public void printError(String errorMessage) {
        printWithLines(errorMessage);
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks the list of tasks to print
     */
    public void printTaskList(TaskList tasks) {
        printWithLines(tasks.toString());
    }

    /**
     * Prints the message to the user after a task has been added.
     *
     * @param task the task that was added
     * @param taskCount the number of tasks in the list
     */
    public void printTaskAdded(Task task, int taskCount) {
        printWithLines("Got it. I've added this task:\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the message to the user after a task has been deleted.
     *
     * @param task the task that was deleted
     * @param taskCount the number of tasks in the list
     */
    public void printTaskDeleted(Task task, int taskCount) {
        printWithLines("Noted. I've removed this task:\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the message to the user after a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void printTaskDone(Task task) {
        printWithLines("Nice! I've marked this task as done:\n\t" + task);
    }

    /**
     * Prints the message to the user after a task has been unmarked as done.
     *
     * @param task the task that was unmarked as done
     */
    public void printTaskUndone(Task task) {
        printWithLines("Ok! I've unmarked this task as done:\n\t" + task);
    }

    /**
     * Prints the message to the user after a task has been found.
     *
     * @param tasks the list of tasks that match the search query
     */
    public void printMatchingTasks(TaskList tasks) {
        printWithLines("Here are the matching tasks in your list:\n" + tasks);
    }

    /**
     * Prints the help message to the user.
     */
    public void printHelpMessage() {
        printWithLines(HELP_MESSAGE);
    }
}