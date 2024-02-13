package natsu.util;

import natsu.task.Task;

public class Printer {

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void printWelcomeMessage() {
        printLine();
        System.out.println("     Hello! I'm Natsu");
        System.out.println("     What can I do for you?");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printTaskAdded(String task, int size) {
        printLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        printLine();
    }

    public static void printTaskDone(String task) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        printLine();
    }

    public static void printTaskUndone(String task) {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        printLine();
    }

    public static void printList(Task[] list, int taskCount) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + list[i].toString());
        }
        printLine();
    }
}
