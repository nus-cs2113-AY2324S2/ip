package gab.ui;

import gab.task.Task;
import gab.task.TaskList;
import java.util.Scanner;

/**
 * Ui class for printing and displaying tasks
 */

public class Ui {
    public static final String LOGO =
                      "  _____           __ \n"
                    + "/  ____|         |  |\n"
                    + "|  |  __   ____  |  |__\n"
                    + "|  | |_  |/  _   |  -   \\ \n"
                    + "|  |__|  |  (_|  | |_)   |\n"
                    + "\\_______ |__ ,_ |_.___ /\n";

    public static final String LINE = "_________________________________________";

    /**
     * Print welcome message
     */

    public static void printWelcome() {
        System.out.println(LOGO);
        System.out.println("\tI am Gab the Bot! Nice to meet you!");
        System.out.println("\tAnything I can help you with?");
    }

    /**
     * Print line after every command
     */

    public static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Retrieve user input to parse
     * @return the task name
     */

    public static String getTask() {
        String taskDescription;
        Scanner in = new Scanner(System.in);
        System.out.println("\tWhat do you want to do?: ");
        taskDescription = in.nextLine();
        return taskDescription;
    }

    /**
     * Method to list task from array list in the correct format
     *
     * @param taskList array list of tasks
     */

    public static void listTask(TaskList taskList) { //using the array to list the tasks
        printLine();
        System.out.println("All your tasks are here");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + taskList.taskList.get(i).toString());
        }
        printLine();
    }

    /**
     * Print task count in the array list
     *
     * @param taskCount total number of task in array list
     */

    public static void printTaskCount (int taskCount) {
        System.out.println("Now you have " + taskCount + " task(s)");
        printLine();
    }

    /**
     * Print message to react to new to do task added
     * @param toDoTask to do task name
     */

    public static void printTodoTask (String toDoTask) {
        printLine();
        System.out.println("Oh no! One new task added...");
        System.out.println(toDoTask);
    }

    /**
     * Print message to react to new deadline task added
     *
     * @param deadlineTask deadline task name
     */

    public static void printDeadlineTask (String deadlineTask) {
        printLine();
        System.out.println("Ok! Watch the deadline!");
        System.out.println(deadlineTask);
    }

    /**
     * Print message to reach to new event task added
     *
     * @param eventTask event task name
     */

    public static void printEventTask (String eventTask) {
        printLine();
        System.out.println("Weehoo! Enjoy the event!");
        System.out.println(eventTask);
    }

    /**
     * Print message when one task is mark as done
     *
     * @param taskIndex task index to mark as done
     * @param taskList array list of tasks
     */

    public static void printMarkTask (int taskIndex, TaskList taskList) {
        printLine();
        System.out.println("One done!");
        System.out.println(taskList.taskList.get(taskIndex).toString());
        printLine();
    }

    /**
     * Print message when one task is mark as not done
     *
     * @param taskIndex task index to mark as not done
     * @param taskList array list of tasks
     */

    public static void printUnmarkTask (int taskIndex, TaskList taskList) {
        printLine();
        System.out.println("Oh no!");
        System.out.println(taskList.taskList.get(taskIndex).toString());
        printLine();
    }

    /**
     * Print message when task is deleted
     *
     * @param taskIndex task index to delete
     * @param taskList array of task list
     */

    public static void printDeleteTask (int taskIndex, TaskList taskList) {
        printLine();
        System.out.println("Ok task deleted!");
        System.out.println("\t" + taskList.taskList.get(taskIndex).toString());
    }

    /**
     * Print respective messages if task is found and not found
     *
     * @param task task class
     * @param taskCount index the task
     * @param isFound whether keyword is found or not
     */

    public static void printFoundTask (Task task, int taskCount, boolean isFound) {
        if (isFound) {
            System.out.println(taskCount + ". " + task);
        } else {
            System.out.println("Yay! No task found...");
        }
    }
}
