package oley.commands;

import oley.tasks.Task;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public static void lineBreaker() {
        System.out.println(" ");
        System.out.println("    ♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥");
    }

    public static void printGreeting() {
        String logo = "  _____  __       \n"
                + " /  _  \\|  | ____ ___  ___ \n"
                + "|  | |  |  |/ ___ \\  \\/  /\n"
                + "|  |_|  |  |  ____/\\    /\n"
                + " \\_____/|__|\\_____|/   /\n"
                + "                  /___/";
        System.out.println("Greetings from\n" + logo);
        lineBreaker();
        System.out.println("    Hello, I'm your cute and lovely friend Oley.");
        System.out.println("    What can I do for you?");
        lineBreaker();
    }

    public static void printFileNotFound() {
        System.out.println("    OOPS, the file does not exist! I will now create one for you~ (๑･∀･๑)");
    }

    public static void printFileNotCreated() {
        System.out.println("    Sorry! I am unable to create file for you.(・ε・｀)");
    }

    public static void printFileCorrupted() {
        System.out.println("    OOPS, the file seems to be corrupted!");
    }

    public static void printError() {
        System.out.println("    OOPS, we have encountered an error!");
    }

    public static void printDeadlineNotSpecified() {
        System.out.println("    A specific deadline would be better for you to complete your task on time! ( •̀ .̫ •́ )✧");
        System.out.println("    You may use /by to indicate the time.");
    }

    public static void printEventNotSpecified() {
        System.out.println("    A specific timing of the event would be clearer! ( •̀ .̫ •́ )✧");
        System.out.println("    You may use /from and /to to indicate the starting and ending time.");
    }

    public static void printFailToWrite() {
        System.out.println("    Write to file failed.");
    }

    public static void printTaskAdded(Task taskName) {
        System.out.println("    " + "added: " + taskName);
    }

    public static void printTaskNumber(int size) {
        if (size <= 1) {
            System.out.println("    Now you have " + size + " task in the list.");
        } else {
            System.out.println("    Now you have " + size + " tasks in the list.");
        }
    }

    public static void printTaskDeleted(String taskToBeDeleted) {
        System.out.println("    Sure! (❛◡❛✿) I have removed this task:");
        System.out.println("    " + taskToBeDeleted);
    }

    public static void printFailToClear() {
        System.out.println("    Clear file failed.");
    }

    public static void exit() {
        System.out.println("    Bye~ Feel free to talk to me anytime. I will always be here waiting for you. ฅʕ•̫͡•");
    }

    public static void printTasks() {
        System.out.println("    Here are the tasks in your list:");
    }

    public static void printTask(int taskNumber, String taskName) {
        System.out.println("    " + taskNumber + "." + taskName);
    }

    public static void printMarkExceedRange(int taskNumber) {
        System.out.println("    You have not created Task " + taskNumber + " yet. Jiayous. I will always support you. ฅ •ﻌ•♡");
    }

    public static void printMarkedAlready() {
        System.out.println("    This task has been marked as done already!");
    }

    public static void printMarked(Task task) {
        System.out.println("    Good job! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public static void printUnmarkFailed() {
        System.out.println("    This task hasn't been done yet!");
    }

    public static void printUnmarked(Task task) {
        System.out.println("    Sure~ I've marked this task as not done yet:");
        System.out.println("    " + task);
    }

    public static void printFailToDelete() {
        System.out.println("    The task you are trying to delete does not exist! (๑•́ ₃•̀๑)");
    }

    public static void printMissingDescription() {
        System.out.println("    The description of a task cannot be empty! (๑•́ ₃•̀๑)");
    }

    public static void printInstructionNotUnderstood() {
        System.out.println("    So sorry, I do not understand the commands. I will try to improve!! (•̀_•́)");
        System.out.println("    Meanwhile, you can use todo, deadline or event to indicate the type of tasks.");
    }

    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        String message;
        message = in.nextLine();
        Ui.lineBreaker();
        return message;
    }
}
