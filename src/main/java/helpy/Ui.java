package helpy;

import helpy.task.Task;
import helpy.task.TaskList;

import java.util.Scanner;

/**
 * A class representing the user interface of the chatbot.
 * Provides methods to interact with the user through the command line.
 */
public class Ui {
    public static final String HORIZONTAL_LINE =
            "___________________________________________________________________________\n";
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
     * Displays a help menu containing all the available commands and their description.
     */
    public void showHelp() {
        String helpInfo = "- help: Shows this help menu\n";
        String byeInfo = "- bye: Exits from the chatbot\n";
        String todoInfo = "- todo <description>: Adds a new todo task with the provided description\n";
        String deadlineInfo = "- deadline <description> /by <d/m/yyyy HHmm>: Adds a " +
                "new task with the\n  specified description and deadline\n";
        String eventInfo = "- event <description> /from <d/m/yyyy HHmm> /to <d/m/yyyy HHmm>: " +
                "Adds a\n  new event with the given description and time period\n";
        String listInfo = "- list: Displays all the tasks in the task list with a task number\n";
        String markInfo = "- mark <task number>: Marks a task as completed\n";
        String unmarkInfo = "- unmark <task number>: Marks a task as not completed\n";
        String deleteInfo = "- delete <task number>: Removes the task from the task list\n";
        String findInfo = "- find <keyword>: Search for tasks containing the provided keyword";

        printMessage("Need some help? Here's the commands that are available (´⊙ω⊙`)\n\n" +
                helpInfo + byeInfo + todoInfo + deadlineInfo + eventInfo + listInfo +
                markInfo + unmarkInfo + deleteInfo + findInfo);
    }

    /**
     * Displays the task list to the user if there are tasks present.
     *
     * @param taskList The object of the task list to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.getListLength() == 0) {
            printMessage("Task list is empty... Add some tasks now! (^.^)");
            return;
        }
        System.out.print(HORIZONTAL_LINE);
        System.out.println("These are the tasks in your list:");
        taskList.printTasks();
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating whether a task has been marked as done or not done.
     *
     * @param task The task that has been marked or unmarked.
     */
    public void showMarkMessage(Task task) {
        if (task.isDone()) {
            printMessage("Good job! \\(^o^)/ I've marked this task as done:\n\t" + task);
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
     * Displays the search results for tasks matching a given query.
     *
     * @param taskList The task list to search.
     * @param query    The keyword to search for.
     */
    public void showFindResult(TaskList taskList, String query) {
        System.out.print(HORIZONTAL_LINE);
        if (taskList.getListLength() >= 1) {
            System.out.println("I found these tasks that match the keyword \"" + query + "\"");
            taskList.printTasks();
        } else {
            System.out.println("No tasks were found with the keyword \"" + query + "\"");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void sayGoodbye() {
        printMessage("Goodbye, see you next time! (^_^)");
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
        printMessage("Hey your description is empty (-_-)");
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
        printMessage("Invalid format for event! Make sure it's in this format:\n" +
                "event <description> /from <d/m/yyyy HHmm> /to <d/m/yyyy HHmm>\n" +
                "e.g. event Overseas trip /from 4/12/2023 0900 /to 11/12/2023 1900");
    }

    public void showEventSeqErr() {
        printMessage("Are you a time traveller (o.O) 'from' date cannot be later than the 'to' date");
    }

    /**
     * Displays an error message when an invalid format is provided for a deadline task.
     */
    public void showDeadlineDescErr() {
        printMessage("Invalid format for deadline! Make sure it's in this format:\n" +
                "deadline <description> /by <d/m/yyyy HHmm>\n" +
                "e.g. deadline read book /by 5/2/2024 1500");
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
