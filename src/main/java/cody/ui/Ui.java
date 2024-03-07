package cody.ui;

import java.util.Scanner;

/**
 * The Ui class handles the user interface and interactions with the user.
 */
public class Ui {
    private static final String BORDER = "______________________________________________________________\n";
    private static final String GREET_MESSAGE = "Hey there! I'm Cody, your personal task manager\n"
            + "Tell me your tasks and I will create a task list for you";
    public static final String HELP_MESSAGE = "Here are the commands you can use:\n"
            + " 1. todo <description>: Adds a todo task to the list\n"
            + " 2. deadline <description> /by <deadline>: Adds a deadline task to the list\n"
            + " 3. event <description> /from <start time> /to <end time>: Adds an event task to the list\n"
            + " 4. list: Displays the list of tasks\n"
            + " 5. mark <index>: Marks the task at the specified index as done\n"
            + " 6. unmark <index>: Unmarks the task at the specified index\n"
            + " 7. delete <index>: Deletes the task at the specified index\n"
            + " 8. find <keyword>: Finds tasks containing the specified keyword\n"
            + " 9. help: Displays the list of commands\n"
            + " 10. bye: Exits the application";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message to the user.
     */
    public void greet() {
        printMessage(GREET_MESSAGE + "\n" + BORDER + HELP_MESSAGE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a message to the user, enclosed in borders.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.print(BORDER + message + "\n" + BORDER);
    }

    /**
     * Prints an exception message to the user.
     *
     * @param e The exception whose message is to be printed.
     */
    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the exit message to the user and closes the scanner.
     */
    public void exit() {
        printMessage(EXIT_MESSAGE);
        scanner.close();
    }
}
