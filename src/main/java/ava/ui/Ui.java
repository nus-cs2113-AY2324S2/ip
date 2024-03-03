package ava.ui;

import ava.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns the command user entered.
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Prints the exception message when an unknown command is entered.
     */
    public void printUnknownCommandExceptionMessage() {
        out.println("(⊙_⊙)? I'm sorry!!! But I don't know what that means.");
    }

    /**
     * Prints the exception message when no '/' is entered after "deadline" and "event".
     */
    public void printDateFormatExceptionMessage() {
        out.println("(⊙_⊙)? You need to specify the date after '/'");
    }

    /**
     * Prints the exception message when the user wants to modify a task number which does not exist.
     */
    public void printTaskNotExistMessage(String type) {
        out.println(" ⊙﹏⊙ Hey! You cannot " + type + " a task that does not exist!");
    }

    /**
     * Prints the exception message when a task name or a task number is not following a command type.
     */
    public void printEmptyTaskNameExceptionMessage(String type) {
        switch (type) {
        case "todo":
            out.println("Please tell me what needs todo (＾＿－)");
            break;
        case "deadline":
            out.println("Please tell me the deadline is for? (＾＿－)");
            break;
        case "event":
            out.println("Please tell me what is the event? (＾＿－)");
            break;
        case "delete":
            out.println("Please tell me which one to delete? (＾＿－)");
            break;
        case "mark":
            out.println("Please tell me which one to mark? (＾＿－)");
            break;
        }
    }

    /**
     * Prints the message after markTask executes successfully.
     */
    public void printAfterMarkingTask(ArrayList<Task> tasks, boolean isMark, int taskChanged) {
        if (isMark) {
            out.println("Nice! I've marked this task as done:");
        } else {
            out.println("OK, I've marked this task as not done yet:");
        }
        out.println(tasks.get(taskChanged));
    }

    /**
     * Prints the message after deleteTask executes successfully.
     */
    public void printAfterDeletingTask(ArrayList<Task> tasks, Task deletedTask) {
        out.println("Noted!!! I've removed this task:");
        out.println(deletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list!!!");
    }

    /**
     * Prints the message after addTask executes successfully.
     */
    public void printAfterAddingTask(ArrayList<Task> tasks) {
        String addedTask = tasks.get(tasks.size() - 1).toString();
        out.println("Got it! I've added this task:");
        out.println(addedTask);

        if (tasks.size() == 1) {
            out.println("Now you have " + 1 + " task in the list~~~");
        } else {
            out.println("Now you have " + tasks.size() + " tasks in the list~~~");
        }
    }

    /**
     * Displays all the tasks in the TaskList.
     * It is called when "list" command is read.
     */
    public void displayTask(ArrayList<Task> tasks) {
        out.println("Here are the tasks in your list:");
        int noOfTask = 0;
        while (noOfTask < tasks.size()) {
            out.println((noOfTask + 1) + "." + tasks.get(noOfTask));
            noOfTask += 1;
        }
    }


    public void printAfterFindingTask(ArrayList<Task> tasks, int noOfMatchedTask, int indexOfTask) {
        if (noOfMatchedTask == 1) {
            out.println("Here are the the matching tasks in your list:");
        }
        out.println((noOfMatchedTask) + "." + tasks.get(indexOfTask));
    }

    public void printAfterNoTaskFound() {
        out.println("No matched task is found!!!");
    }

    /**
     * Prints the greeting message.
     */
    public void greet() {
        printLine();
        out.println(" Hello!!! AvavaAVA!!! Here is Ava!!!");
        out.println(" Let's have a relaxing and happy chat together!!!");
        out.println(" What can I do for you?");
        printLine();
    }

    /**
     * Prints the exiting message.
     */
    public void exit() {
        out.println(" Bye!!! Hope to see you again soon!!!");
        printLine();
    }

    /**
     * Prints a line formed by '_'.
     */
    public void printLine() {
        out.println("____________________________________________________________");
    }
}
