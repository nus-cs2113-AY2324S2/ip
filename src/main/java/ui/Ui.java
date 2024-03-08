package ui;
import todo.Todo;

public class Ui {

    public Ui() {

    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreetings() {
        printLine();
        System.out.println("Hello! I'm Doraemon!");
        System.out.println("What can I do for you?");
        printLine();
    }
    public static void printMessage(Todo task, int taskNum) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.formatTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        printLine();
    }
    public static void printDelMessage(Todo task, int taskNum) {
        System.out.println("Got it. I've deleted this task:");
        System.out.println("  " + task.formatTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
    }
    public static void printMarked(Todo task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.formatTask());
    }
    public static void printExit() {
        System.out.println("Bye. Have a great day!");
        printLine();
    }
}
