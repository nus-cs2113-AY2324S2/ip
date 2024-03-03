package RuntimeSupport;
import Event.Task;
import Event.TaskList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the user interface for the Duke application. The <code>Ui</code> class
 * is responsible for all interactions with the user, including displaying messages,
 * errors, and reading user input.
 * It provides a simple text-based interface through the console.
 */
public class Ui {
    private final Scanner scanner;
    static String BREAK_LINE = "____________________________________________________________";

    /**
     * Initializes a new Ui instance for handling input and output.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a predefined break line string to the console to separate
     * different parts of the output visually.
     */
    public void showLine() {
        System.out.println(BREAK_LINE);
    }

    /**
     * Reads a line of text inputted by the user.
     *
     * @return a string containing the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows an error message to the user when there is a problem loading
     * tasks from the storage file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
        System.out.println(BREAK_LINE);
    }

    /**
     * Prints a welcome message to the user upon starting the application.
     */
    public void showWelcome() {
        System.out.println(BREAK_LINE);
        System.out.println("Hello! I'm 550W.\nWhat can I do for you?");
        System.out.println(BREAK_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        System.out.println(message);
        System.out.println(BREAK_LINE);
    }

    /**
     * Shows a goodbye message to the user, typically used when exiting
     * the application.
     */
    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon! ;)");
        System.out.println(BREAK_LINE);
    }

    /**
     * Informs the user that a task has been added successfully to their
     * task list.
     *
     * @param task The task that was added.
     * @param size The new total number of tasks in the user's task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Voilà! Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (size) + " tasks in the list.");
        System.out.println(BREAK_LINE);
    }

    /**
     * Notifies the user that a specific task has been marked as done.
     *
     * @param task The task that has been marked as completed.
     */
    public void markTaskDone(Task task) {
        System.out.println("X marks the spot! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(BREAK_LINE);
    }

    /**
     * Notifies the user that a specific task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void markTaskUndone(Task task) {
        System.out.println("All good! We've hit the rewind button and unmarked this task:");
        System.out.println("  " + task);
        System.out.println(BREAK_LINE);
    }

    /**
     * Informs the user that a task has been successfully removed from their
     * task list.
     *
     * @param task The task that was removed.
     * @param size The new total number of tasks in the user's task list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(BREAK_LINE);
    }

    /**
     * Displays the user's entire task list, showing each task and its details.
     *
     * @param tasks The user's task list to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Abracadabra! Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(BREAK_LINE);
    }

    /**
     * Displays the results of a search query to the user. If matching tasks are
     * found, it lists each task and its details.
     * If no tasks match the query, it informs the user that no such tasks exist
     * in their task list.
     *
     * @param tasks The list of tasks that match the search criteria. This list
     * could be empty if no tasks match the search criteria.
     */
    public void showFoundResults(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Ah! There are no such tasks in my storage list!");
        } else {
            System.out.println("Here you go! Those are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(BREAK_LINE);
    }
}
