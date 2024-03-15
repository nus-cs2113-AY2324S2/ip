package Byte.ui;

import Byte.task.TaskList;

/**
 * Represents the user interface for the Byte chatbot application.
 */
public class Ui {
    /**
     * Prints a welcome message to the user.
     */
    public void printWelcomeMessage() {
        printLineSeparator();
        System.out.println("Hello! I'm Byte, your friendly chat assistant!");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    /**
     * Prints the tasks in the task list.
     *
     * @param tasks The task list to print.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    /**
     * Prints a line separator.
     */
    public void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a response message to the user.
     *
     * @param response The response message to print.
     */
    public void printResponse(String response) {
        printLineSeparator();
        System.out.println(response);
    }

    /**
     * Prints an error message to the user.
     *
     * @param errorMessage The error message to print.
     */
    public void printError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Prints a loading error message when the file is not found.
     */
    public void printLoadingError() {
        System.out.println("File not found. Starting with an empty task list.");
    }

}
