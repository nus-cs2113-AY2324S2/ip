package helper;

import exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UserInterface class provides methods to interact with the user through the console.
 * It displays messages and prompts, and reads user input.
 */

public class UserInterface {
    private static final String LINE = "-----------------------------------------";
    private static final String TAB_SPACE = "    ";
    public static final int INDEX_OFFSET = 1;
    public static final int START_INDEX = 0;
    private static final String CHATBOT_NAME = "ZORO";


    public static void greetUser() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Hello I'm " + CHATBOT_NAME);
        System.out.println(TAB_SPACE + "What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads user input from the console.
     *
     * @param in The Scanner object to read input.
     * @return The user input as a String.
     */

    public static String getUserInput(Scanner in) {
        return in.nextLine().trim();
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param totalTasks The total number of tasks after adding the new task.
     */

    public static void printTaskAdded(Task task, int totalTasks) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I've added this task:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(TAB_SPACE + "Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param totalTasks The total number of tasks after removing the task.
     */

    public static void printTaskRemoved(String task, int totalTasks) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Noted. I've removed this task:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(TAB_SPACE + "Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints the list of tasks that has been saved so far .
     *
     * @param taskList The list of tasks to be printed.
     * @param index The index of the last task in the list.
     */

    public static void printTaskList(ArrayList<Task> taskList, int index) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Here are the tasks in your list:");
        for (int i = START_INDEX; i < index; i++) {
            System.out.println(TAB_SPACE + (i + INDEX_OFFSET) + "." + taskList.get(i));
        }
        System.out.println(LINE);
    }


    public static void printTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Nice! I've marked this task as done:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(LINE);
    }

    public static void printTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "OK, I've marked this task as not done yet:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an invalid task index.
     *
     * @param e The exception containing the error message.
     */

    public static void printInvalidTaskIndex(IndexOutOfBoundsException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage() + ".\n" + TAB_SPACE + "Please check your input.");
        System.out.println(LINE);
    }

    public static void sayGoodbye() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an invalid event format.
     *
     * @param e The exception containing the error message.
     */

    public static void printInvalidEventFormat(InvalidEventFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage()
                + "Please use: event [description] /from [start date/time] /to [end date/time]");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an invalid deadline format.
     *
     * @param e The exception containing the error message.
     */

    public static void printInvalidDeadlineFormat(InvalidDeadlineFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage() + "Please use: deadline [description] /by [date/time]");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an invalid task type.
     *
     * @param taskDescription The invalid task description.
     */

    public static void printInvalidTaskType(String taskDescription) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid task type: " + taskDescription);
        System.out.println(TAB_SPACE + "Please mention the task type (todo, deadline, or event) or \n"
                + TAB_SPACE + "use commands (list, mark, unmark, bye)");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an invalid todo format.
     *
     * @param e The exception containing the error message.
     */

    public static void printInvalidTodoFormat(InvalidTodoFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage() + "Please dont put empty description");
        System.out.println(LINE);
    }


    public static void printTaskAlreadyMarked(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "NOTE: " + message);
        System.out.println(LINE);
    }


    public static void printTaskAlreadyUnmarked(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "NOTE: " + message);
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an invalid input index.
     *
     * @param e The exception containing the error message.
     */

    public static void printInvalidInputIndex(NumberFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid task index: " + e.getMessage());
        System.out.println(TAB_SPACE + "Please enter a valid task index to mark or unmark.");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an error occurred while loading a file.
     *
     * @param e The exception containing the error message.
     */

    public static void printLoadFileError(LoadFileException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Unable to load File. " + e.getMessage());
        System.out.println(TAB_SPACE + "Please Check your File path.");
        System.out.println(TAB_SPACE + "Continuing will create a new file in the destination path");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an error occurred while saving a file.
     *
     * @param e The exception containing the error message.
     */


    public static void printUnableToSave(SaveFileException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Unable to Save File. " + e.getMessage());
        System.out.println(TAB_SPACE + "Please Check your File path");
        System.out.println(LINE);
    }

    /**
     * This prints the tasks that has been filtered using the given keyword
     * It also gives the Original Index of the task to
     * facilitate the user for other actions
     *
     * @param foundTasks Tasks that has filtered out
     * @param taskList The list of all tasks.
     */

    public static void printFoundTasks(ArrayList<Task> foundTasks, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            int originalIndex = taskList.indexOf(foundTasks.get(i)) + INDEX_OFFSET;
            System.out.println(
                    TAB_SPACE + (i + INDEX_OFFSET) + foundTasks.get(i) + ". (Original Index: " + originalIndex + ") ");
        }
        System.out.println(LINE);
    }

    public static void printInvalidDateTimeFormat(InvalidDateTimeFormatException e){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid format: " + e.getMessage());
        System.out.println(TAB_SPACE + "Please use valid date and time format DD/MM/YYY HH:MM ");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating an error with the find keyword given by the user.
     *
     * @param e The exception containing the error message.
     */

    public static void printInvalidKeywordFormat(StringIndexOutOfBoundsException e){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid format: " + e.getMessage());
        System.out.println(TAB_SPACE + "Please use valid keyword.");
        System.out.println(LINE);
    }


}
