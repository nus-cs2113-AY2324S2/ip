package ui;
import tasklist.todo.Todo;

/**
 * Deals with all the printing of communication with user.
 */
public class Ui {

    public Ui() {}
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void printGreetings() {
        printLine();
        System.out.println("Hello! I'm Doraemon!");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printMessage(Todo task, int taskNum) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.formatTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        printLine();
    }

    public void printDelMessage(Todo task, int taskNum) {
        System.out.println("Got it. I've deleted this task:");
        System.out.println("  " + task.formatTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
    }

    public void printMarked(Todo task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.formatTask());
    }

    public void printExit() {
        System.out.println("Bye. Have a great day!");
        printLine();
    }

    public void showIOException() {
        System.out.println("Something went wrong, please try again!");
        printLine();
    }

    public void showIsEmptyException() {
        System.out.println("Please type something! Try: todo/deadline/event to add a record!");
        printLine();
    }

    public void showInValidCommandException() {
        System.out.println("Invalid Command! Try: todo/deadline/event to add a record!");
        printLine();
    }

    public void showEmptyListException() {
        System.out.println("The list is empty! Please add something!");
        printLine();
    }

    public void showMakeDir() {
        System.out.println("No previous data was found, a new file is created.");
        printLine();
    }
}