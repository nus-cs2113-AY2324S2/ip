package mimi.helper;

import mimi.classes.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class is responsible for handling the user interface of the application.
 *
 * @author Justin
 * @version 0.2
 * @since 0.2
 */
public class Ui {

    private Boolean isRunning;
    private Scanner scanner;
    final int INPUT_LIMIT = 2;
    final String INPUT_DELIMITER = " ";

    public Ui() {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        printWelcomeMessage();
    }

    private void printWelcomeMessage() {
        System.out.println("-------------------------------------------");
        System.out.println("Hello! I'm mimichat");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------");
    }

    /**
     * Method is used to print the goodbye message to the user.
     * This method is called when the user enters 'bye'.
     */
    public void shutdownSequence() {
        printDescription("Bye. Hope to see you again soon!");
        this.setRunning(false);
        this.scanner.close();
    }

    public Boolean isRunning() {
        return isRunning;
    }

    /**
     * Method is used to set the running status of the application.
     * Set it to true if you want the application to continue running.
     * Set it to false if you want the application to stop running.
     *
     * @param running the status of the application
     */
    public void setRunning(Boolean running) {
        isRunning = running;
    }

    /**
     * Method is used to get the input from the user.
     * The input is then split into an array of strings based on the delimiter and limit.
     * The input is limited to 2 strings mainly the taskType and the other parameters.
     * The other parameters will be processed in the later part of the code.
     *
     * @return an array of strings containing the input from the user
     */
    public String[] getInput() {
        String input = this.scanner.nextLine();
        return input.split(INPUT_DELIMITER, INPUT_LIMIT);
    }

    private static String formatTask(Task task) {
        return "\t" + task;
    }

    /**
     * Method is used to format the task in the following manner
     * [index]. [task]
     * e.g. 1. [T][✓] read book
     *      2. [D][✗] return book (by: June 6th)
     *
     * @param task the task to be formatted
     * @param index the index of the task in the taskList
     * @return a formatted string of the task
     */
    private static String formatTask(Task task, int index) {
        String indexNumber = Integer.toString(index + 1);
        return indexNumber + ". " + task;
    }

    /**
     * Method is used to print the list of tasks in taskList
     * If the list is empty, print a message to the user that the list is empty.
     * @param taskList the list of tasks
     */
    private static void printTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("\u001B[31mYou have no tasks in your list\u001B[0m");
        }
        else {
            for (Task t : taskList) {
                System.out.println(formatTask(t, taskList.indexOf(t)));
            }
        }
    }

    /**
     * Method is used when a task is added or removed from the list.
     * @param task the task that was added or removed
     * @param taskList the list of tasks
     */
    private static void printMessage(Task task, ArrayList<Task> taskList) {
        System.out.println("\t" + formatTask(task));
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " tasks in the list");
    }

    /**
     * Method is used in the shutdown sequence to print a message to the user.
     * Can be used to print any message to the user with special formatting.
     *
     * @param input the description of the task
     */
    public static void printDescription(String input) {
        System.out.println("-------------------------------------------");
        System.out.println(input);
        System.out.println("-------------------------------------------");
    }

    /**
     * Method is used to when a task (todo/deadline/event) is added to the taskList
     * Use this method to print a message to the user when a task is added.
     *
     * @param task the task that was added
     * @param taskList the list of tasks
     */
    public static void printSuccessMessage(Task task, ArrayList<Task> taskList) {
        System.out.println("-------------------------------------------");
        System.out.println("Got it. I've added this task:");
        printMessage(task, taskList);
        System.out.println("-------------------------------------------");
    }

    /**
     * Method is used to when a task (todo/deadline/event) is removed from the taskList
     * Use this method when you want to print a message to the user that a task has been removed.
     *
     * @param task the task that was added
     * @param taskList the list of tasks
     */
    public static void printDeleteMessage(Task task, ArrayList<Task> taskList) {
        System.out.println("-------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        printMessage(task, taskList);
        System.out.println("-------------------------------------------");
    }

    /**
     * Method is used to when a task (todo/deadline/event) is marked as done
     * Use this method when you want to print a message to the user that a task has been marked as done.
     *
     * @param taskList the list of tasks
     * @param index the index of the task that was marked as done
     */
    public static void printMarkTask(ArrayList<Task> taskList, int index) {
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as done");
        System.out.println(formatTask(taskList.get(index), index));
        System.out.println("-------------------------------------------");
    }

    /**
     * Method is used to when a task (todo/deadline/event) is marked as undone
     * Use this method when you want to print a message to the user that a task has been marked as undone.
     *
     * @param taskList the list of tasks
     * @param index the index of the task that was marked as undone
     */
    public static void printUnmarkTask(ArrayList<Task> taskList, int index) {
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(formatTask(taskList.get(index), index));
        System.out.println("-------------------------------------------");
    }

    /**
     * Use this method to print a message to the user of all the task based of  a keyword search
     *
     * @param taskList the list of tasks
     */
    public static void printTaskFound(ArrayList<Task> taskList) {
        System.out.println("-------------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        printTasks(taskList);
        System.out.println("-------------------------------------------");
    }

    /**
     * Use this method to print a message to the user of all the tasks in the taskList
     *
     * @param taskList the list of tasks
     */
    public void listTasks(ArrayList<Task> taskList) {
        System.out.println("-------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        printTasks(taskList);
        System.out.println("-------------------------------------------");
    }


}
