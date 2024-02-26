package geepee.task.list;

import java.util.ArrayList;
import geepee.system.SystemMessage;
import geepee.task.Task;

public abstract class ListMessage {

    /**
     * Prints message to acknowledge the addition of a new task.
     */
    private static void printTaskAddedMessage(Task task) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + task);
    }

    /**
     * Prints message to acknowledge the removal of a task.
     */
    private static void printTaskRemovedMessage(Task task) {
        System.out.println("    Noted. I've removed this task: ");
        System.out.println("      " + task);
    }

    /**
     * Prints the size of the list.
     */
    private static void printListSummary(int size) {
        System.out.println("    Now you have " + size + " task" + (size == 1 ? "" : "s")  + " in the list.");
    }

    /**
     * Prints a task.
     */
    protected static void printTask(Task task, int number) {
        System.out.println("    " + number + "." + task);
    }

    /**
     * Prints message to acknowledge changing a task's completion status.
     */
    protected static void printTaskStatusMessage(boolean isDone, Task task) {
        System.out.println(SystemMessage.getHorizontalLine());
        if (isDone) {
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + task);
        System.out.println(SystemMessage.getHorizontalLine());
    }

    /**
     * Prints a series of messages after adding a new task into the list.
     */
    protected static void printAfterAddingTask(int size, Task task) {
        System.out.println(SystemMessage.getHorizontalLine());
        printTaskAddedMessage(task);
        printListSummary(size);
        System.out.println(SystemMessage.getHorizontalLine());
    }

    /**
     * Prints a series of messages after removing a task from the list.
     */
    protected static void printAfterRemovingTask(int size, Task task) {
        System.out.println(SystemMessage.getHorizontalLine());
        printTaskRemovedMessage(task);
        printListSummary(size);
        System.out.println(SystemMessage.getHorizontalLine());
    }

    /**
     * Prints tasks that are relevant to a keyword.
     */
    protected static void printRelevantTasks(ArrayList<Task> tasks) {
        System.out.println(SystemMessage.getHorizontalLine());
        System.out.println("    Here are the relevant tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printTask(tasks.get(i), i + 1);
        }
        if (tasks.size() == 0) {
            System.out.println("    No relevant tasks found!");
        }
        System.out.println(SystemMessage.getHorizontalLine());
    }

    /**
     * Prints all the tasks currently in the list.
     */
    protected static void printAllTasks(ArrayList<Task> tasks) {
        System.out.println(SystemMessage.getHorizontalLine());
        System.out.println("    Here are the current tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printTask(tasks.get(i), i + 1);
        }
        if (tasks.size() == 0) {
            System.out.println("    There are no tasks in your list!");
        }
        System.out.println(SystemMessage.getHorizontalLine());
    }
}
