package geepee.task.list;

import geepee.system.SystemMessage;
import geepee.task.Task;

public class ListMessage {

    private static void printTaskAddedMessage(Task task) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + task);
    }

    private static void printTaskRemovedMessage(Task task) {
        System.out.println("    Noted. I've removed this task: ");
        System.out.println("      " + task);
    }

    private static void printListSummary(int size) {
        System.out.println("    Now you have " + size + " task" + (size == 1 ? "" : "s")  + " in the list.");
    }

    protected static void printTaskStatusMessage(boolean isDone, Task task) {
        SystemMessage.printHorizontalLine();
        if (isDone) {
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + task);
        SystemMessage.printHorizontalLine();
    }

    protected static void printAfterAddingTask(int size, Task task) {
        SystemMessage.printHorizontalLine();
        printTaskAddedMessage(task);
        printListSummary(size);
        SystemMessage.printHorizontalLine();
    }

    protected static void printAfterRemovingTask(int size, Task task) {
        SystemMessage.printHorizontalLine();
        printTaskRemovedMessage(task);
        printListSummary(size);
        SystemMessage.printHorizontalLine();
    }

    public static void printAllTasks(List list) {
        SystemMessage.printHorizontalLine();
        System.out.println("    Here are the current tasks in your list:");
        for (int i = 0; i < list.tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + list.tasks.get(i));
        }
        if (list.tasks.size() == 0) {
            System.out.println("    There are no tasks in your list!");
        }
        SystemMessage.printHorizontalLine();
    }
}
