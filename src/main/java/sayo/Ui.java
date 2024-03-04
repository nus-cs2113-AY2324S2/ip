package sayo;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Ui class handles the user interface for the Sayo application.
 * It manages the input and output in the console.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for Ui class.
     * Initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method to print a horizontal line for UI separation.
     */
    public void showLine() {
        System.out.println("-----------------------------------------------");
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        showLine();
        System.out.println("Hello! I'm Sayo and it's great to see you! \nWhat can I do for you today?\n");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("You have no saved tasks! \nStart using me :)");
        showLine();
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showList(TaskList items) {
        showLine();
        for (int i = 0; i < items.getSize(); i++) {
            System.out.println((i + 1) + ". " + items.getTask(i));
        }
        showLine();
    }

    /**
     * Retrieves the next line of text entered by the user.
     *
     * @return The trimmed user input as a String.
     */
    public String getUserCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showToUser(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked.
     */
    public void showTaskMarked(Task task) {
        showLine();
        System.out.println("Awesome! I've marked this task as done: ");
        System.out.println(task);
        showLine();
    }
    
    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println("Awesome! I've unmarked this task: ");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays a message confirming that a new task has been added.
     * 
     * @param todo The todo task that was added.
     * @param items The current list of tasks, used to display the new total count.
     */
    public void showAddedTaskMessage(ToDo todo, TaskList items) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
        showLine();
    }

    /**
     * Prompts the user to enter the deadline in the correct format.
     */
    public void requestDeadlineFormat() {
        showLine();
        System.out.println("Please enter the deadline in the format: deadline <deadline description> /by <due date>");
        showLine();
    }

    /**
     * Displays a message confirming that a new deadline task has been added.
     * 
     * @param deadline The deadline task that was added.
     * @param items The current list of tasks, used to display the new total count.
     */
    public void showAddedDeadlineMessage(Deadline deadline, TaskList items) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
        showLine();
    }

    /**
     * Prompts the user to enter the event in the correct format.
     */
    public void requestEventFormat() {
        showLine();
        System.out.println("Please enter the event in the format: event <event description> /from <start time> /to <end time>");
        showLine();
    }

    /**
     * Displays a message confirming that a new event task has been added.
     * 
     * @param event The event task that was added.
     * @param items The current list of tasks, used to display the new total count.
     */
    public void showAddedEventMessage(Event event, TaskList items) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message confirming that a task has been removed.
     * 
     * @param task The task that was removed.
     * @param items The current list of tasks, used to display the new total count.
     */
    public void showDeletedTaskMessage(Task task, TaskList items) {
        showLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
        showLine();
    }

    /**
     * Informs the user of the correct format to delete a task.
     */
    public void requestDeleteFormat() {
        showLine();
        System.out.println("Invalid task number. Please enter a valid task number to delete.");
        showLine();
    }

    /**
     * Closes the scanner object to prevent resource leaks.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a list of tasks found by keyword search.
     *
     * @param tasks The list of tasks that match the search keyword.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found!");
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
        showLine();
    }
}
