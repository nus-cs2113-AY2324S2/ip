package bob;

import java.util.List;

/**
 * Represents the user interface of the program.
 */
public class Ui {
    public static final String logo = " ____       _        \n"
            + "|  _ \\_____|_|__ _\n"
            + "| |_| | /\\ | |    \\ \n"
            + "| |_| | \\/ | |  O /\n"
            + "|____/ \\__,|_|\\__/\n";
    public static final String TODO_ICON = "[T]";
    public static final String DEADLINE_ICON = "[D]";
    public static final String EVENT_ICON = "[E]";

    /**
     * Constructor for the Ui class.
     */
    public void displayList(List<Task> list) {
        displayHorizontalLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }

        displayHorizontalLine();
    }

    /**
     * Displays the welcome message of the program.
     */
    public void displayWelcomeMessage() {

        System.out.println("Hello from\n" + logo);

        displayHorizontalLine();
        System.out.println("Hello! I'm Bob. Your personal task manager.");
        System.out.println("What can I do for you?");
        displayHorizontalLine();
    }

    /**
     * Displays the exit message of the program.
     */
    public void displayExitMessage() {
        displayHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayHorizontalLine();
    }

    /**
     * Displays the horizontal line.
     */
    public void displayHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the error message for adding a task.
     * @param e the error message
     */
    public void displayErrorMessage(BobException e) {
        displayHorizontalLine();
        System.out.println(e.getMessage());
        displayHorizontalLine();
    }

    /**
     * Displays the message for saving a list.
     * @param filename The filename that was saved
     */
    public void displaySaveMessage(String filename) {
        displayHorizontalLine();
        System.out.println("Your list has been saved to " + filename + ".");
        displayHorizontalLine();
    }
}