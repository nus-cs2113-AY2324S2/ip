package helpy;

import helpy.task.Task;
import helpy.task.TaskList;

import java.util.Scanner;

/**
 * A class representing the user interface of the chatbot.
 * Provides methods to interact with the user through the command line.
 */
public class Ui {
    public static final String HORIZONTAL_LINE = "______________________\n";
    public static final String name =
            "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
            "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
            "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";

    /**
     * Prints a message between two horizontal lines above and below it.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Greets the user with a personalised message.
     */
    public void greetUser() {
        printMessage("Greetings, I am\n" + name + "\nHow can I help you?\n");
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Displays the task list to the user.
     *
     * @param taskList The object of the task list to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        int label = 1;

        System.out.print(HORIZONTAL_LINE);
        System.out.println("These are the tasks in your list:");
        for (Task task : taskList.getTasks()) {
            System.out.print(label + ".");
            System.out.println(task);
            label++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating whether a task has been marked as done or not done.
     *
     * @param task The task that has been marked or unmarked.
     */
    public void showMarkMessage(Task task) {
        if (task.isDone()) {
            printMessage("Good job! I've marked this task as done:\n\t" + task);
        } else {
            printMessage("Ok, this task has been marked as not done yet:\n\t" + task);
        }
    }

    /**
     * Displays a message confirming the addition of a task to the list.
     *
     * @param addedTask  The task that has been added.
     * @param numOfTasks The total number of tasks in the list after the addition.
     */
    public void showAddMessage(Task addedTask, int numOfTasks) {
        String taskGrammar = (numOfTasks > 1) ? " are " + numOfTasks + " tasks " : " is 1 task ";
        printMessage("Ok I just added:\n\t" + addedTask + "\nThere" + taskGrammar + "in the list.");
    }

    /**
     * Displays a message confirming the deletion of a task from the list.
     *
     * @param removedTask The task that has been removed.
     * @param numOfTasks  The total number of tasks remaining in the list after the deletion.
     */
    public void showDeleteMessage(Task removedTask, int numOfTasks) {
        String remainingTasks = "";
        switch (numOfTasks) {
        case 0:
            remainingTasks = "There are no more tasks left in the list.";
            break;
        case 1:
            remainingTasks = "There is 1 task left in the list.";
            break;
        default:
            remainingTasks = "There are " + numOfTasks + " tasks left in the list.";
        }
        printMessage("Got it, I've removed this task from the list:\n" +
                "\t" + removedTask + "\n" + remainingTasks);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void sayGoodbye() {
        printMessage("Goodbye, see you next time!");
    }

    /**
     * Displays an error message when an unknown command is entered.
     *
     * @param fullCommand The unknown command entered by the user.
     */
    public void showUnknownCommandErr(String fullCommand) {
        printMessage("I don't understand the command \"" + fullCommand
                + "\". Can you check that you typed correctly?");
    }

    /**
     * Displays an error message when an empty description is provided for a task.
     */
    public void showIllegalDescriptionErr() {
        printMessage("Hey your task description is empty!");
    }

    /**
     * Displays an error message when an IOException occurs during file writing.
     */
    public void showIOExceptionErr() {
        printMessage("Error when attempting to write task to file");
    }

    /**
     * Displays an error message when an invalid format is provided for an event task.
     */
    public void showEventDescErr() {
        printMessage("Invalid format for event! Make sure it's in this format: " +
                "event <description> /from <start date> /to <end date>");
    }

    /**
     * Displays an error message when an invalid format is provided for a deadline task.
     */
    public void showDeadlineDescErr() {
        printMessage("Invalid format for deadline! Make sure it follows: " +
                "deadline <description> /by <date>");
    }

    /**
     * Displays an error message when an invalid task number is provided.
     */
    public void showInvalidTaskNumErr() {
        printMessage("Task number provided is invalid!");
    }

    /**
     * Displays an error message when a non-existent task number is provided.
     */
    public void showAbsentTaskNumErr() {
        printMessage("Task number doesn't exist!");
    }
}
