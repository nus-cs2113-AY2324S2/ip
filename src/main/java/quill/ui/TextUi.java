package quill.ui;

import quill.task.Task;
import quill.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String NAME = "Quill";
    private final Scanner in;

    public TextUi() {
        this.in = new Scanner(System.in);
    }

    public String getUserCommand() {
        return in.nextLine();
    }
    public static void showWelcomeMessage() {
        System.out.println(DIVIDER + "\nHello! I'm " + NAME + ".");
        System.out.println("What can i do for you?\n" + DIVIDER);
    }

    public static void showAddTask(Task tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + TaskList.getTotalTasks() + " tasks in the list.");
    }

    public static void showDeleteTask(Task tasks) {
        System.out.println("Got it. I've removed this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + (TaskList.getTotalTasks() - 1) + " tasks in the list.");
    }

    public static void showDivider() {
        System.out.println(DIVIDER);
    }

    public static void showGoodbyeMessage() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Zero tasks. Add something already.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.getTotalTasks(); i++) {
                System.out.println(i + 1 + "." + tasks.getTask(i).toString());
            }
        }
    }

    public static void showFindList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Nothing. Tough luck.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        }
    }

}
