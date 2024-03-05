package ui;

import java.util.ArrayList;
import java.util.Scanner;
import taskList.TaskList;
import tasks.Task;

/**
 * Handles all user interactions with KikuBot.
 * This class is responsible for displaying messages to the user,
 * reading user input, and showing tasks.
 */
public class Ui {
    private Scanner scanner;
    private static final String HORIZONTAL = "____________________________________________________________";
    private static final String BOT_NAME = "Kiku";

    /**
     * Constructs a new Ui instance, initializing the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message to the user at the start of the application.
     */
    public void greetingMessage() {
        System.out.println(HORIZONTAL);
        System.out.println("Hello! I'm " + BOT_NAME
                + "! How can I assist you today?");
        System.out.println(HORIZONTAL);
    }

    /**
     * Displays the exit message to the user upon quitting the application.
     * Called when command is instance of exitCommand
     */
    public void exitMessage() {
        System.out.println("Goodbye! " +
                "Have a good day and hope to see you again soon :)");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed depending on context.
     */
    public void errorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The line of input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a task or message to the user.
     *
     * @param task The task or message to be displayed.
     */
    public void showTask(String task) {
        System.out.println(task);
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @param taskList The current task list to be displayed.
     */
    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list: ");
        System.out.println(taskList.getFormattedTasks());
    }

    /**
     * Displays tasks that match the search keyword.
     *
     * @param matchingTasks An ArrayList of all tasks in the current task list that match the search query.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("OOPS! No matching tasks found :<");
        } else {
            System.out.println("Alright! Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
