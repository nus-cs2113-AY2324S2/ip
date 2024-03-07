package bobby;

import java.util.ArrayList;

/**
 * Represents the user interface for interacting with the Bobby application.
 */
public class Ui {
    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello I'm Bobby\n" + "What can I do for you?");
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void showByeMessage() {
        System.out.println("See you again soon!");
    }

    /**
     * Displays an error message when there is an issue saving changes.
     */
    public void showSavingError() {
        System.out.println("Error saving changes.");
    }

    /**
     * Displays an error message for an invalid command input.
     */
    public void showInvalidCommandError() {
        System.out.println("Sorry, I didn't quite understand that.\nPlease enter a valid command.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param list  The list of tasks.
     * @param entry The index of the task.
     */
    public void showMarkMessage(ArrayList<Task> list, int entry) {
        System.out.println("Marked as done");
        System.out.println(entry + "." + list.get(entry - 1));
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param list  The list of tasks.
     * @param entry The index of the task.
     */
    public void showUnmarkMessage(ArrayList<Task> list, int entry) {
        System.out.println("Unmarked");
        System.out.println(entry + "." + list.get(entry - 1));
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays an error message for an invalid todo input format.
     */
    public void showInvalidTodoMessage() {
        System.out.println("Please enter a valid todo in this format:");
        System.out.println("todo Attend CS2113 tutorial");
    }

    /**
     * Displays a message indicating a valid todo task has been added.
     *
     * @param tasks The list of tasks.
     */
    public void showValidTodoMessage(ArrayList<Task> tasks) {
        System.out.println("Okay, added:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Displays an error message for an invalid deadline input format.
     */
    public void showInvalidDeadlineMessage() {
        System.out.println("Please enter a valid deadline task in this format:");
        System.out.println("deadline Return Book /by Sunday");
    }

    /**
     * Displays a message indicating a valid deadline task has been added.
     *
     * @param tasks The list of tasks.
     */
    public void showValidDeadlineMessage(ArrayList<Task> tasks) {
        System.out.println("Okay, added:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Displays an error message for an invalid event input format.
     */
    public void showInvalidEventMessage() {
        System.out.println("Please enter a valid event in this format:");
        System.out.println("event Project Meeting /from Mon 2pm /to 4pm");
    }

    /**
     * Displays a message indicating a valid event task has been added.
     *
     * @param tasks The list of tasks.
     */
    public void showValidEventMessage(ArrayList<Task> tasks) {
        System.out.println("Okay, added:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param tasks The list of tasks.
     * @param entry The index of the task.
     */
    public void showDeleteMessage(ArrayList<Task> tasks, int entry) {
        System.out.println("Noted, I've removed this task:\n" + tasks.get(entry - 1));
        System.out.println("Now you have " + (tasks.size() - 1) + " task(s) in the list.");
    }

    /**
     * Displays an error message when there is an issue loading saved tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading saved tasks, file not found");
    }

    /**
     * Displays an error message for an issue with a text file.
     */
    public void showTextFileError() {
        System.out.println("Something went wrong :(");
    }

    public void showNoMatchMessage() {
        System.out.println("No matching tasks found.");
    }

    public void showMatchMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
