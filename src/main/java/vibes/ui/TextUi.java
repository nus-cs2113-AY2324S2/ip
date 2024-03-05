package vibes.ui;

import vibes.task.type.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    private static final String CHATBOT_NAME = "Vibes";
    public static final String DASHED_LINE = "\t---------------------------------------------------------------------------------------";

    public  void showByeMessage() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println(DASHED_LINE);
    }

    public void showTaskAddedMessage(ArrayList<Task> tasks) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + tasks.get(tasks.size() - 1));
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks(ArrayList<Task> tasks) {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
    }

    public void showMarkedMessage(ArrayList<Task> tasks, int taskNumber) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void showUnmarkedMessage(ArrayList<Task> tasks, int taskNumber) {
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void showDeletedMessage(ArrayList<Task> tasks, Task taskToDelete) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + taskToDelete);
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showWelcomeMessage() {
        System.out.println(DASHED_LINE);
        System.out.println("\t Hello! I'm " + CHATBOT_NAME + "\n\t What can I do for you?");
        System.out.println(DASHED_LINE);
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    public void showError(String message)   {
        System.out.println(message);
    }
}
