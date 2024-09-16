import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class represents a simple user interface for interacting with a task management system.
 * It provides methods for printing messages to the console, reading user input, and managing tasks.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object.
     * Initializes the scanner to read from the standard input stream.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a greeting message to the console.
     */
    public void printHello() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Sura");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a line of input from the user.
     * @return the input string
     */
    public String readCommand() {
        String input = "";
        while (true) {
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
        }
    }

    /**
     * Prints a line separator to the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message to the console.
     * @param errorMessage the error message to print
     */
    public void printError(String errorMessage) {
        System.out.println("____________________________________________________________");
        System.out.println("Error: " + errorMessage);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message indicating a failure to load tasks from a file.
     */
    public void printLoadingError() {
        System.out.println("____________________________________________________________");
        System.out.println("Error loading tasks from file.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a list of tasks to the console.
     * @param tasks the list of tasks to print
     */
    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a confirmation message indicating a task has been added to the list.
     * @param task the task that was added
     * @param tasks the updated list of tasks
     */
    public void printTaskAdded(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a confirmation message indicating a task has been removed from the list.
     * @param task the task that was removed
     * @param tasks the updated list of tasks
     */
    public void printTaskRemoved(Task task, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    public void printTasksMarked(Task task, ArrayList<Task> tasks) {
        System.out.println("I have marked this task as done.");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }
    public void printTasksUnmarked(Task task, ArrayList<Task> tasks) {
        System.out.println("I have unmarked this task.");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }
    public void printInvalidTaskNumber(int currentTaskCount) {
        System.out.println("Invalid task number! You only have " + currentTaskCount + " tasks.");
    }


}
