package Yoj.ui;
import Yoj.taskList.*;
import Yoj.tasks.Task;

import java.util.ArrayList;

public class Ui {
    /**
     * Prints a long line for formatting.
     */
    public static void printLine() {
        System.out.println("________________________________________");
    }
    /**
     * Prints a short line for formatting.
     */
    public static void printShortLine() {
        System.out.println("_____________");
    }
    /**
     * Prints an introduction, logo and user prompt.
     */
    public static void printHello() {
        String logo =
                "__   __   ___    _____ \n"
                        + "\\ \\ / /  / _ \\  | ___ |\n"
                        + " \\ Y /  | | | |     | | \n"
                        + "  \\ /   | | | |     | | \n"
                        + "  | |   | |_| |  ___| | \n"
                        + "  |_|    \\___/  |____/          \n";
        printLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm YOJ");
        System.out.println("What can I do for you?");
        printShortLine();
    }
    /**
     * Prints a message indicating that the task has been successfully added to the task list.
     */
    public static void addTaskMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(List.tasks.get(List.tasks.size() - 1));
        System.out.println("Now you have " + List.tasks.size() + " tasks in the list.");
    }
    /**
     * Prints a message after task is marked as done.
     * @param index The index of the specific task in the arraylist.
     */
    public static void markDoneMessage(int index) {
        System.out.println("okiee! I've marked this task as done: ");
        System.out.println(List.tasks.get(index-1).taskType() + "[X] " + List.tasks.get(index - 1).getDescription());
    }
    /**
     * Prints a message after task is marked as undone.
     * @param index The index of the specific task in the arraylist.
     */
    public static void markUndoneMessage(int index) {
        System.out.println("alright I've marked this task as not done yet, do rmb to do it soon: ");
        System.out.println(List.tasks.get(index-1).taskType() + "[ ] " + List.tasks.get(index - 1).getDescription());
    }
    /**
     * Prints the list of current tasks.
     */
    public static void printList() {
        if (List.tasks.size() == 0) {
            System.out.println("the list is currently empty...");
            System.out.println("add your tasks below :)");
        } else {
            for (int i = 0; i < List.tasks.size(); i++) {
                System.out.println(i + 1 + ". " + List.tasks.get(i).toString());
            }
        }
    }
    /**
     * Prints message to inform user of tasks deleted.
     * @param deletedTask The specific description of the deleted task
     */
    public static void taskDeletedMessage(Task deletedTask) {
        if (List.tasks.size() == 0) {
            System.out.println("okiee i've deleted the task");
            System.out.println(deletedTask);
            System.out.println("there's no more tasks in the list.. please add new tasks below :)");
        } else {
            System.out.println("okiee i've deleted the task");
            System.out.println(deletedTask);
            System.out.println("Now you have " + List.tasks.size() + " tasks in the list.");
        }
    }
    /**
     * Prints out tasks containing specific keyword based on user input.
     * @param tasksFound The list of tasks that contain the keyword.
     */
    public static void printTaskFound(ArrayList<Task> tasksFound) {
        System.out.println("here are the tasks found with the keyword!");
        for (int i = 0; i < tasksFound.size(); i++) {
            System.out.println(i + 1 + ". " + tasksFound.get(i));
        }
    }
    /**
     * Prints message to inform user that there are no tasks with similar keyword.
     */
    public static void printNoTaskFound() {
        System.out.println("there are no such tasks found...");
    }
    /**
     * Prints exit message.
     */
    public static void byeMessage() {
        System.out.println("bye bye!! hope to see u soon :)");
    }
    /**
     * Prints message to inform user that there is no existing File.
     */
    public static void printFilenotFound() {
        System.out.println("No existing file found. A new file will be created.");
    }
}
