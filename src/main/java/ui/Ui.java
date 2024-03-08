package ui;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the chatbot.
 */
public class Ui {

    private final String name;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new Ui with the given name.
     * @param name The name of the chatbot.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Displays a greeting message.
     */
    public void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    /**
     * Reads the user's command from the input.
     *
     * @return The user's command.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("=========================");
    }

    /**
     * Displays the number of tasks in the list.
     *
     * @param size The number of tasks.
     */
    public void showSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message for a task that has been added.
     *
     * @param description The description of the task.
     */
    public void showTaskAdded(String description) {
        System.out.println("Added: " + description);
    }
    // Error messages:

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays a message for a task that is not found.
     */
    public void showTaskNotFound() {
        System.out.println("Task not found.");
    }

    // invalid input

    /**
     * Displays an error message for an invalid command.
     */
    public void invalidCommand() {
        System.out.println("Invalid command! Please try again.");
    }

    /**
     * Displays an error message for an invalid task format.
     *
     * @param stage The stage of the task (task, todo, event, deadline).
     */
    public void invalidTaskFormat(String stage) {
        switch (stage) {
            case "task":
                System.out.println("Invalid task format.");
                break;
            case "todo":
                System.out.println("Invalid TODO format. Please add description for todo");
                break;
            case "event":
                System.out.println("Invalid EVENT format. Please include the start and end times.");
                break;
            case "deadline":
                System.out.println("Invalid DEADLINE format. Please include the due time.");
                break;
            default:
                break;
        }
    }

    /**
     * Displays an error message for an invalid index.
     */
    public void invalidIndex() {
        System.out.println("Invalid index!");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("=========================");
    }
}