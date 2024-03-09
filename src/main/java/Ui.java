import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all user interactions, which include reading user input
 * and displaying messages to the user
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcome(String chatBotName) {
        printLine();
        System.out.println("Helloooo! I'm " + chatBotName);
        System.out.println("What can I do for you today?");
        printLine();
    }

    public void showGoodbye() {
        printLine();
        System.out.println("Byeeee. Hope to see you again soon!:)");
        printLine();
    }

    public void showLoadingError() {
        printLine();
        System.out.println("Error loading tasks from file.");
        printLine();
    }

    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        printLine();
    }

    public void showAddedTask(Task task, int numberOfTasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        printLine();
    }

    public void showMarkedTask(Task task, boolean isDone) {
        printLine();
        System.out.println("OK, I've marked this task as " + (isDone ? "done" : "not done yet") + ":");
        System.out.println("  " + task);
        printLine();
    }

    public void showDeletedTask(Task task, int numberOfTasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        printLine();
    }

    public void showFoundTasks(ArrayList<Task> foundTasks) {
        printLine();
        if (foundTasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + "." + foundTasks.get(i));
            }
        }
        printLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}
