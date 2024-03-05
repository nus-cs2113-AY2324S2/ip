package vibes.ui;

import vibes.task.TaskList;
import vibes.task.type.Task;

public class TextUi {
    public static void showTaskAddedMessage() {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + TaskList.tasks.get(TaskList.tasks.size() - 1));
        System.out.println("\t Now you have " + TaskList.tasks.size() + " tasks in the list.");
    }

    public static void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + TaskList.tasks.get(i));
        }
    }

    public static void showMarkedMessage(int taskNumber) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + TaskList.tasks.get(taskNumber));
    }

    public static void showUnmarkedMessage(int taskNumber) {
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + TaskList.tasks.get(taskNumber));
    }

    public static void showDeletedMessage(Task taskToDelete) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + taskToDelete);
        System.out.println("\t Now you have " + TaskList.tasks.size() + " tasks in the list.");
    }
}
