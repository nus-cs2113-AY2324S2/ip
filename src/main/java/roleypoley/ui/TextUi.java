package roleypoley.ui;

import roleypoley.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {

    public static void printAddReply(ArrayList<Task> taskList) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  [" + taskList.getLast().getTaskTypeIcon() + "][ ] " + taskList.getLast().getDescription());
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printDelReply(ArrayList<Task> taskList, int taskNum) {
        System.out.println("\t Got it. I've deleted this task:");
        System.out.println("\t  [" + taskList.get(taskNum).getTaskTypeIcon() + "][ ] " + taskList.get(taskNum).getDescription());
        System.out.println("\t Now you have " + (taskList.size() - 1) + " tasks in the list.");
    }

    public static void displayList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("\tLooks like you need to find more work to do! Task list is empty!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i) == null) {
                    break;
                }
                System.out.println("\t" + (i + 1) + ".[" + taskList.get(i).getTaskTypeIcon() + "]["
                        + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
            }
        }
    }

    public static void createLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void welComeMessage() {
        System.out.println("Hello! I'm RoleyPoley \nWhat can I do for you today?");
        createLine();
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

}
