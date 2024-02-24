package quill.ui;

import quill.task.Task;

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
        System.out.println(DIVIDER + "\nGot it. I've added this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.\n" + DIVIDER);
    }

    public static void showDeleteTask(Task tasks) {
        System.out.println(DIVIDER + "\nGot it. I've removed this task:");
        System.out.println(tasks.toString());
        Task.removeTask();
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.\n" + DIVIDER);
    }

    public static void showDivider() {
        System.out.println(DIVIDER);
    }

    public static void showGoodbyeMessage() {
        System.out.println(DIVIDER + "\nBye! Hope to see you again soon!\n" + DIVIDER);
    }
}
