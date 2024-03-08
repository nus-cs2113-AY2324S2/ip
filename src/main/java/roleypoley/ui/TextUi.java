package roleypoley.ui;

import roleypoley.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of application
 */
public class TextUi {

    /**
     * Prints a message to indicate task that have been added
     * @param taskList ArrayList that the task is being added to
     */
    public static void printAddReply(ArrayList<Task> taskList) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  [" + taskList.get(taskList.size() - 1).getTaskTypeIcon() + "][ ] " + taskList.get(taskList.size() - 1).getDescription());
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints a message to indicate task that have been deleted
     * @param taskList ArrayList that the task is being deleted from
     * @param taskNum Index of task that is being deleted
     */
    public static void printDelReply(ArrayList<Task> taskList, int taskNum) {
        System.out.println("\t Got it. I've deleted this task:");
        System.out.println("\t  [" + taskList.get(taskNum).getTaskTypeIcon() + "][ ] " + taskList.get(taskNum).getDescription());
        System.out.println("\t Now you have " + (taskList.size() - 1) + " tasks in the list.");
    }

    /**
     * Prints out all the tasks in the ArrayList
     * @param taskList ArrayList that stores the tasks
     * @param isFind Determines if ArrayList is the filtered or unfiltered list
     */
    public static void displayList(ArrayList<Task> taskList, boolean isFind) {
        if (taskList.isEmpty()) {
            System.out.println("\tLooks like there is no task available! You should stop slacking and add it in!");
        } else if (isFind) {
            System.out.println("\tHere are the matching tasks in your list:");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) {
                break;
            }
            System.out.println("\t" + (i + 1) + ".[" + taskList.get(i).getTaskTypeIcon() + "]["
                    + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
        }
    }

    public static void createLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    /**
     * Prints a welcome message to the user upon initialisation of application
     */
    public static void welComeMessage() {
        System.out.println("Hello! I'm RoleyPoley \nWhat can I do for you today?");
        createLine();
    }

    /**
     * Reads in the user input
     * @return user input
     */
    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

}
