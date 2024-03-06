package com.arriky.app;

import com.arriky.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class of Arriky chatbot to display execution outcomes on the screen and take user's input.
 * @author Songyue Wang
 * @version 1.0
 */
public class UI {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Get the input from the user in CLI.
     * @return The line of command input by the user.
     */
    public static String getCommand() {
        return scanner.nextLine();
    }

    /**
     * Display greeting message.
     */
    public static void greet() {
        printSeparation();
        System.out.println(
                " Hello! I'm Arriky\n" +
                        " What can I do for you?"
        );
        printSeparation();
    }

    /**
     * Display goodbye message.
     */
    public static void endSession() {
        System.out.println(" Bye. Hope to see you again soon.");
        printSeparation();
    }

    /**
     * Display separation line.
     */
    public static void printSeparation() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Display acknowledgement when new task is added.
     * Print the summary of the last element in tasklist.
     * @param taskList Tasklist used in the main programme.
     */
    public static void printInsertionAcknowledgement(TaskList taskList) {
        int size = taskList.getTaskCount();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + taskList.getSummaryByIndex(size - 1));
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * Display acknowledgement when new task is deleted.
     * @param taskList Tasklist used in the main programme.
     * @param summary Summary of the task that has been deleted.
     */
    public static void printDeletionAcknowledgement(TaskList taskList, String summary) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + summary);
        System.out.println(" Now you have " + taskList.getTaskCount() + " tasks in the list.");
    }

    /**
     * Display acknowledgement when new task is marked as done.
     * @param taskList Tasklist used in the main programme.
     * @param index Index of the task that is marked as done.
     */
    public static void printMarkDoneAcknowledgement(TaskList taskList, int index) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + taskList.getSummaryByIndex(index));
    }

    /**
     * Display acknowledgement when new task is unmarked as done.
     * @param taskList Tasklist used in the main programme.
     * @param index Index of the task that is marked as done.
     */
    public static void printUnmarkDoneAcknowledgement(TaskList taskList, int index) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(" " + taskList.getSummaryByIndex(index));
    }

    /**
     * Print error message if a runtime error is encountered.
     * @param prompt the error message to be displayed.
     */
    public static void displayError(String prompt) {
        System.out.println("~X~ Bot Error: " + prompt);
    }

    /**
     * Print acknowledgement message on successfully import of task list from local file.
     */
    public static void displayImportSuccess() {
        System.out.println("Saved entries imported!");
    }

    /**
     * List all entries in the tasklist.
     * @param results An arraylist containing summaries of all tasks in tasklist.
     */
    public static void listAllTasks(ArrayList<String> results) {
        if (results.isEmpty()) {
            System.out.println("Your task list is empty. Please add one first");
        } else {
            System.out.println(" Here are all the entries in your task list:");
            int displayId = 1;
            for (String entry : results) {
                System.out.println(" " + displayId + ". " + entry);
                displayId++;
            }
        }
    }

    /**
     * List all entries in the find result in the tasklist.
     * @param results An arraylist containing query results.
     */
    public static void displayFindResults(ArrayList<String> results) {
        if (results.isEmpty()) {
            System.out.println("No matching entry found in your task list.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            int displayId = 1;
            for (String entry : results) {
                System.out.println(" " + displayId + ". " + entry);
                displayId++;
            }
        }
    }
}
