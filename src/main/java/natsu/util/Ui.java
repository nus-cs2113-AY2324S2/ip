package natsu.util;

import static natsu.util.TaskList.list;

public class Ui {

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

    public static void printTaskAdded(String task) {
        printLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
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

    public static void printList() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "." + list.get(i).toString());
        }
        printLine();
    }

    public static void printTaskDeleted(String task, int size) {
        printLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        printLine();
    }
}
