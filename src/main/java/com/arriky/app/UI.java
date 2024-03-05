package com.arriky.app;

import com.arriky.task.TaskList;
import java.util.Scanner;

public class UI {

    static Scanner scanner = new Scanner(System.in);

    public static String getCommand() {
        return scanner.nextLine();
    }

    public static void greet() {
        printSeparation();
        System.out.println(
                " Hello! I'm Arriky\n" +
                        " What can I do for you?"
        );
        printSeparation();
    }

    public static void endSession() {
        System.out.println(" Bye. Hope to see you again soon.");
        printSeparation();
    }

    public static void printSeparation() {
        System.out.println("____________________________________________________________");
    }

    public static void printInsertionAcknowledgement(TaskList taskList) {
        int size = taskList.getTaskCount();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + taskList.getSummaryByIndex(size - 1));
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public static void printDeletionAcknowledgement(TaskList taskList, String summary) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + summary);
        System.out.println(" Now you have " + taskList.getTaskCount() + " tasks in the list.");
    }

    public static void printMarkDoneAcknowledgement(TaskList taskList, int index) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + taskList.getSummaryByIndex(index));
    }

    public static void printUnmarkDoneAcknowledgement(TaskList taskList, int index) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(" " + taskList.getSummaryByIndex(index));
    }

    public static void displayError(String prompt) {
        System.out.println("~X~ Bot Error: " + prompt);
    }

    public static void displayImportSuccess() {
        System.out.println("Saved entries imported!");
    }

    public static void listAllTasks(TaskList taskList) {
        int size = taskList.getTaskCount();
        for (int i=0; i<size; i++) {
            int displayID = i + 1;
            System.out.println(" " + displayID + ". " + taskList.getSummaryByIndex(i));
        }
    }

}
