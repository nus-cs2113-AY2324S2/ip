package Bobble;

import Bobble.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles the user interface interactions, including displaying messages and responses.
 */
public class Ui {
    public static final String LINE_WRAP = "____________________________________________________________\n";

    /**
     * Prints an error message when one or more fields of a task are empty.
     *
     * @param command The command used by the user.
     */
    public void printEmptyFieldErrorMessage(String command) {
        System.out.println(LINE_WRAP +
                "OOPS!! One or more fields of a(n) " + command + " cannot be empty.\n" + LINE_WRAP);
    }

    /**
     * Prints an error message when an unknown command is entered.
     */
    public void printErrorMessage() {
        System.out.println(LINE_WRAP + "OOPS!! I'm sorry, but I don't know what that means :-( " +
                "Do you have a typo?\n" + LINE_WRAP);
    }

    /**
     * Prints a message when there is an error while loading the tasks.
     */
    public void showLoadingError() {
        System.out.println(LINE_WRAP + "Unable to find list, making a new one for you :)");
    }

    /**
     * Displays the current list of tasks.
     *
     * @param taskList The task list to be displayed.
     */
    public void listResponse(ArrayList<Task> taskList) {
        System.out.println(LINE_WRAP + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
        System.out.println(LINE_WRAP);
    }

    /**
     * Prints a response after marking a task as done.
     *
     * @param task The task that has been marked as done.
     */
    public void printMarkResponse(String task) {
        System.out.println("Nice! I've marked this task as done:\n" +
                task + "\n" + LINE_WRAP);
    }

    /**
     * Prints a response after marking a task as not done.
     *
     * @param task The task that has been unmarked.
     */
    public void printUnmarkResponse(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString() + "\n" + LINE_WRAP);
    }

    /**
     * Prints a response after adding a new task to the task list.
     *
     * @param taskList The task list that has been updated.
     * @param task The task that has just been added.
     */
    public void addTaskResponse(ArrayList<Task> taskList, Task task) {
        System.out.println(LINE_WRAP +
                "Got it. I've added this task: \n  " + task.toString() + "\nNow you have " + taskList.size() +
                " task(s) in the list.\n" + LINE_WRAP);
    }

    /**
     * Prints a response after deleting a new task from the task list.
     *
     * @param taskList The task list that has been updated.
     * @param task The task that has just been deleted.
     */
    public void printDeleteResponse(ArrayList<Task> taskList, Task task) {
        System.out.println(LINE_WRAP + "Noted. I've removed this task:\n" + task.toString() +
                "\nNow you have " + (taskList.size() - 1) + " task(s) in the list.\n" + LINE_WRAP);
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println(LINE_WRAP + "Hello! I'm Bobble\n" + "What can I do for you?\n" + LINE_WRAP);
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println(LINE_WRAP +
                "Bye. Hope to see you again soon!\n" + LINE_WRAP);
    }
}
