package sayo;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Sayo and it's great to see you! \nWhat can I do for you today?\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("You have no saved tasks! \nStart using me :)");
    }

    public String getUserCommand() {
        return scanner.nextLine().trim();
    }

    public void showToUser(String message) {
        System.out.println(message);
    }

    public void showTaskMarked(Task task) {
        System.out.println("Awesome! I've marked this task as done: ");
        System.out.println(task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Awesome! I've unmarked this task: ");
        System.out.println(task);
    }

    public void showAddedTaskMessage(ToDo todo, TaskList items) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
    }

    public void requestDeadlineFormat() {
        System.out.println(
                "Please enter the deadline in the format: deadline <deadline description> /by <due date>");
    }

    public void showAddedDeadlineMessage(Deadline deadline, TaskList items) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
    }

    public void requestEventFormat() {
        System.out.println(
                "Please enter the event in the format: event <event description> /from <start time> /to <end time>");
    }

    public void showAddedEventMessage(Event event, TaskList items) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
    }

    public void showDeletedTaskMessage(Task task, TaskList items) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + items.getSize() + " tasks in the list.");
    }

    public void requestDeleteFormat() {
        System.out.println("Invalid task number. Please enter a valid task number to delete.");
    }

    public void closeScanner() {
        scanner.close();
    }
}
