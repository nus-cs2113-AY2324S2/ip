package ui;

import taskPackage.Task;
import java.util.List;

/**
 * Handles user interface operations such as printing task lists and messages.
 */
public class Ui {
    private static final String HORIZONTAL_LINES = "____________________________________________________________";

    /**
     * Prints the list of tasks.
     *
     * @param list The list of tasks to print.
     */
    public void printTaskList(List<Task> list) {
        int listSize = list.size();
        if (listSize == 0) {
            printFormattedMessage("No tasks added. Add now!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < listSize; i++) {
                Task task = list.get(i);
                System.out.println(" " + (i + 1) + ". "
                        + task.getStatusIcon() + " " + task.getDescription());
            }
            System.out.println(HORIZONTAL_LINES);
        }
    }

    /**
     * Prints a formatted message.
     *
     * @param message The message to print.
     */
    public void printFormattedMessage(String message) {
        System.out.println(message);
        System.out.println(HORIZONTAL_LINES);
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println(HORIZONTAL_LINES);
        String chatbotName = "EDITH";
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINES);
    }
}
