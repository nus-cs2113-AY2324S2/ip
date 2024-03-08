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
    private static final String HELP_MESSAGE = "The valid commands you can use are:\n" +
            "\tbye\n" +
            "\tlist\n" +
            "\ttodo <description>\n" +
            "\tdeadline <description> /by <date>\n" +
            "\tevent <description> /from <date> /to <date>\n" +
            "\tmark <task number>\n" +
            "\tunmark <task number>\n" +
            "\tdelete <task number>";
    private static final String USER_INPUT_INDICATOR = "\t-> ";
    private static final String WELCOME_MESSAGE = "Hello! I'm Schmidt\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Goodbye! Hope to see you again soon!";
    private static final String DONE_TASK_MESSAGE = "Nice! I've marked this task as done:\n\t";
    private static final String UNDONE_TASK_MESSAGE = "Ok! I've unmarked this task as done:\n\t";
    private static final String MATCHING_TASKS_MESSAGE = "Here are the matching tasks in your list:\n";
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
     * @param message the message to print.
     */
    public void printWithLines(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints a message to the user.
     *
     * @param message the message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command from the user.
     *
     * @return the command read from the user.
     */
    public String readCommand() {
        System.out.print(USER_INPUT_INDICATOR);
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcome() {
        printMessage(LOGO);
        printWithLines(WELCOME_MESSAGE);
    }

    /**
     * Prints the goodbye message to the user.
     */
    public void printGoodbye() {
        printWithLines(GOODBYE_MESSAGE);
    }

    /**
     * Prints an error message to the user.
     *
     * @param errorMessage the error message to print.
     */
    public void printError(String errorMessage) {
        printWithLines(errorMessage);
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks the list of tasks to print.
     */
    public void printTaskList(TaskList tasks) {
        printWithLines(tasks.toString());
    }

    /**
     * Prints the message to the user after a task has been added.
     *
     * @param task the task that was added.
     * @param taskCount the number of tasks in the list.
     */
    public void printTaskAdded(Task task, int taskCount) {
        printWithLines("Got it. I've added this task:\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the message to the user after a task has been deleted.
     *
     * @param task the task that was deleted.
     * @param taskCount the number of tasks in the list.
     */
    public void printTaskDeleted(Task task, int taskCount) {
        printWithLines("Noted. I've removed this task:\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the message to the user after a task has been marked as done.
     *
     * @param task the task that was marked as done.
     */
    public void printTaskDone(Task task) {
        printWithLines(DONE_TASK_MESSAGE + task);
    }

    /**
     * Prints the message to the user after a task has been unmarked as done.
     *
     * @param task the task that was unmarked as done.
     */
    public void printTaskUndone(Task task) {
        printWithLines(UNDONE_TASK_MESSAGE + task);
    }

    /**
     * Prints the message to the user after a task has been found.
     *
     * @param tasks the list of tasks that match the search query.
     */
    public void printMatchingTasks(TaskList tasks) {
        printWithLines(MATCHING_TASKS_MESSAGE + tasks);
    }

    /**
     * Prints the help message to the user.
     */
    public void printHelpMessage() {
        printWithLines(HELP_MESSAGE);
    }
}