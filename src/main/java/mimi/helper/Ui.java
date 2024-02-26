package mimi.helper;

import mimi.classes.Task;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void shutdownSequence() {
        printDescription("Bye. Hope to see you again soon!");
        this.setRunning(false);
        this.scanner.close();
    }

    public String[] getInput() {
        String input = this.scanner.nextLine();
        return input.split(INPUT_DELIMITER, INPUT_LIMIT);
    }

    public Boolean isRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private static String formatTask(Task task) {
        return "\t" + task;
    }

    private static String formatTask(Task task, int index) {
        String indexNumber = Integer.toString(index + 1);
        return indexNumber + ". " + task;
    }

    private static void printMessage(Task task, ArrayList<Task> taskList){
        System.out.println("\t" + formatTask(task));
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " tasks in the list");
    }


    // PUBLIC ACCESSIBLE METHODS FOR PRINTING
    public void printTasks(ArrayList<Task> taskList){
        for (Task t : taskList) {
            System.out.println(formatTask(t, taskList.indexOf(t)));
        }
    }

    public static void printMarkTask(ArrayList<Task> list, int index) {
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as done");
        System.out.println(formatTask(list.get(index), index));
        System.out.println("-------------------------------------------");
    }

    public static void printUnmarkTask(ArrayList<Task> list, int index) {
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(formatTask(list.get(index), index));
        System.out.println("-------------------------------------------");
    }

    public static void printDescription(String input) {
        System.out.println("-------------------------------------------");
        System.out.println(input);
        System.out.println("-------------------------------------------");
    }


    public static void printSuccessMessage(Task task, ArrayList<Task> taskList) {
        System.out.println("-------------------------------------------");
        System.out.println("Got it. I've added this task:");
        printMessage(task, taskList);
        System.out.println("-------------------------------------------");
    }

    public static void printDeleteMessage(Task task, ArrayList<Task> taskList) {
        System.out.println("-------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        printMessage(task, taskList);
        System.out.println("-------------------------------------------");
    }

    public void listTasks(ArrayList<Task> taskList) {
        System.out.println("-------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        printTasks(taskList);
        System.out.println("-------------------------------------------");
    }


}
