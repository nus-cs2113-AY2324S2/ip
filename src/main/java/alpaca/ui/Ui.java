package alpaca.ui;

import alpaca.parser.Parser;
import alpaca.tasks.Task;
import alpaca.tasks.TaskList;
import java.util.Scanner;

/**
 * Manages the user interface for the application, handling input and output operations.
 */
public class Ui {
    private static final String LOGO = "           _      _____        _____          \n" +
        "     /\\   | |    |  __ \\ /\\   / ____|   /\\    \n" +
        "    /  \\  | |    | |__) /  \\ | |       /  \\   \n" +
        "   / /\\ \\ | |    |  ___/ /\\ \\| |      / /\\ \\  \n" +
        "  / ____ \\| |____| |  / ____ \\ |____ / ____ \\ \n" +
        " /_/    \\_\\______|_| /_/    \\_\\_____/_/    \\_\\\n" +
        "                                              \n" +
        "                                              ";

    private static final String HORIZONTAL_LINE = "_____________" +
        "_______________________________________________\n";

    private static final String GREET_MESSAGE = "Baa-baa-baa, I'm Alpaca!\n"
        + "How can I assist you today?\n";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon, baa-baa-baa!\n";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initiates listening for user input and processing commands.
     *
     * @param parser The parser to interpret user commands.
     */
    public void listenForInput(Parser parser) {
        while (scanner.hasNextLine()) {
            String receivedMessage = scanner.nextLine().trim();
            parser.parseCommand(receivedMessage);
        }
    }

    /**
     * Prints a predefined horizontal line for UI separation.
     */
    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the greeting message with the application logo.
     */
    public static void printGreeting() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GREET_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows the farewell message to the user.
     */
    public static void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param taskList The task list to be displayed.
     */
    public static void printTaskList(TaskList taskList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        System.out.print(taskList.listTasks());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays tasks that match a given keyword.
     *
     * @param keyword The keyword to filter tasks.
     * @param taskList The task list containing potential matches.
     */
    public static void printMatchingTaskList(String keyword, TaskList taskList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        System.out.print(taskList.listMatchingTasks(keyword));
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Informs the user about a task addition.
     *
     * @param task The task that was added.
     * @param listCount The total number of tasks after addition.
     */
    public static void printAddTask(Task task, int listCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println(HORIZONTAL_LINE);
    }
}
