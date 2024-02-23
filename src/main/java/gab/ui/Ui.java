package gab.ui;

import gab.task.Task;
import gab.task.TaskList;
import java.util.Scanner;

public class Ui {
    public static final String LOGO =
                      "  _____           __ \n"
                    + "/  ____|         |  |\n"
                    + "|  |  __   ____  |  |__\n"
                    + "|  | |_  |/  _   |  -   \\ \n"
                    + "|  |__|  |  (_|  | |_)   |\n"
                    + "\\_______ |__ ,_ |_.___ /\n";

    public static void printWelcome() {
        System.out.println(LOGO);
        System.out.println("\tI am Gab the Bot! Nice to meet you!");
        System.out.println("\tAnything I can help you with?");
    }

    public static final String LINE = "_________________________________________";

    public static void printLine() {
        System.out.println(LINE);
    }
    public static String getTask() {
        String taskDescription;
        Scanner in = new Scanner(System.in);
        System.out.println("\tWhat do you want to do?: ");
        taskDescription = in.nextLine();
        return taskDescription;
    }

    public static void listTask(TaskList taskList) { //using the array to list the tasks
        printLine();
        System.out.println("All your tasks are here");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + taskList.taskList.get(i).toString());
        }
        printLine();
    }

    public static void printTaskCount (int taskCount) {
        System.out.println("Now you have " + taskCount + " task(s)");
        printLine();
    }

    public static void printTodoTask (String toDoTask) {
        printLine();
        System.out.println("Oh no! One new task added...");
        System.out.println(toDoTask);
    }

    public static void printDeadlineTask (String deadlineTask) {
        printLine();
        System.out.println("Ok! Watch the deadline!");
        System.out.println(deadlineTask);
        printLine();
    }

    public static void printEventTask (String eventTask) {
        printLine();
        System.out.println("Weehoo! Enjoy the event!");
        System.out.println(eventTask);
        printLine();
    }

    public static void printMarkTask (int taskIndex, TaskList taskList) {
        printLine();
        System.out.println("One done!");
        System.out.println(taskList.taskList.get(taskIndex).toString());
        printLine();
    }

    public static void printUnmarkTask (int taskIndex, TaskList taskList) {
        printLine();
        System.out.println("Oh no!");
        System.out.println(taskList.taskList.get(taskIndex).toString());
        printLine();
    }

    public static void printDeleteTask (int taskIndex, TaskList taskList) {
        printLine();
        System.out.println("Ok task deleted!");
        System.out.println("\t" + taskList.taskList.get(taskIndex).toString());
        printLine();
    }

    public static void printFoundTask (Task task, int taskCount, boolean isFound) {
        printLine();
        if (isFound) {
            System.out.println("All your matching tasks are here: ");
            System.out.println(taskCount + ". " + task);
        } else {
            System.out.println("Yay! No task found...");
        }
        printLine();
    }
}
