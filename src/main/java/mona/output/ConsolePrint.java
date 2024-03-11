package mona.output;

import mona.task.Task;
import mona.util.Constants;

import java.util.ArrayList;

public class ConsolePrint {

    public static void printHorizontalLine() {
        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }

    public static void printUnmarkStatement(Task task) {
        printHorizontalLine();

        System.out.println(Constants.UNMARK_TASK_MESSAGE);
        System.out.println(task);

        printHorizontalLine();
    }

    public static void printMarkStatement(Task task) {
        printHorizontalLine();

        System.out.println(Constants.MARK_TASK_MESSAGE);
        System.out.println(task);

        printHorizontalLine();
    }

    public static void printAddTaskStatement(Task task, int noOfTasks) {
        printHorizontalLine();

        System.out.println(Constants.ADD_TASK_MESSAGE + System.lineSeparator() + task);
        System.out.println("Now you have " + Integer.toString(noOfTasks) + " tasks in the list.");

        printHorizontalLine();
    }

    public static void printDeleteTaskStatement(Task task, int noOfTasks) {
        printHorizontalLine();

        System.out.println(Constants.DELETE_TASK_MESSAGE + System.lineSeparator() + task);
        System.out.println("Now you have " + Integer.toString(noOfTasks) + " tasks in the list.");

        printHorizontalLine();
    }

    public static void printList(ArrayList<Task> tasks) {
        printHorizontalLine();

        System.out.println(Constants.LIST_TASKS_MESSAGE);

        int index = 1;
        for (Task task: tasks) {
            if (task != null) {
                System.out.println(Integer.toString(index) + "." + task);
                index += 1;
            }
        }
        printHorizontalLine();
    }

    public static void printFilteredList(ArrayList<Task> filteredTasks, ArrayList<Task> tasks, String keyword) {
        printHorizontalLine();

        if (filteredTasks.isEmpty()) {
            System.out.printf(Constants.NO_TASKS_FOUND_MESSAGE, keyword);
            System.out.println();
        } else {
            System.out.println(Constants.TASKS_FOUND_MESSAGE);

            for (Task task: filteredTasks) {
                if (task != null) {
                    int index = tasks.indexOf(task) + 1;
                    System.out.println(Integer.toString(index) + "." + task);
                }
            }
        }
        printHorizontalLine();
    }

    public static void printErrorMessage(String errorMessage) {
        printHorizontalLine();

        System.out.println(errorMessage);

        printHorizontalLine();
    }

    public static void greet() {
        System.out.println("Hello from\n" + Constants.ASCII_LOGO);

        printHorizontalLine();

        System.out.println("Hello! I'm Mona");
        System.out.println("What can I do for you?");

        printHorizontalLine();
    }

    public static void exit() {
        printHorizontalLine();

        System.out.println(Constants.EXIT_MESSAGE);

        printHorizontalLine();
    }

    public static void printHelpMessage() {
        printHorizontalLine();

        System.out.println(Constants.HELP_MESSAGE);

        printHorizontalLine();
    }
}
