package bean.ui;

import bean.task.Task;
import bean.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String MESSAGE_TASK_UNDONE = "    Oops, looks like you're still not done with this:\n   ";
    private static final String MESSAGE_TASK_ADDED = "    Hey, I've added:\n    ";
    private static final String MESSAGE_TASK_DELETED = "    Alright, this task has been removed:\n    ";
    private static final String SEPARATOR = "   -------------------------------------------------";
    private static final String MESSAGE_WELCOME = "    Hello! I'm Bean.\n    What can I do for you?";
    private static final String MESSAGE_LOADING_TASKS = "    Loading tasks...";
    private static final String MESSAGE_SAVING_TASKS = "    Saving tasks...";
    private static final String MESSAGE_LIST_HEADER = "    These are the tasks in your list:";
    private static final String MESSAGE_TASK_DONE = "    Hey, looks like you're done with this task:\n   ";
    private static final String MESSAGE_CURRENT_NUMTASKS = "    You currently have ";
    private static final String MESSAGE_FIND_TASKS = "    Hey, these are the matching tasks in your list:";
    private static final String EXCEPTION_INVALID_TASK_NUMBER = "    Sorry, that was an invalid task number.";
    private static final String EXCEPTION_NO_VALUE_FOR_REQUIRED_FIELDS = "    Exception: no value for required fields";
    private static final String EXCEPTION_NO_SUCH_COMMAND = "    Sorry, I don't understand that command";
    private static final String EXCEPTION_IO = "     IO exception occurred: ";
    private static final String MESSAGE_GOODBYE = "    Bean will take a nap now. Bye!";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        System.out.println(SEPARATOR);
    }

    public static void printLoadingTasks() {
        System.out.println(MESSAGE_LOADING_TASKS);
        System.out.println(SEPARATOR);
    }

    public static void printSavingTasks() {
        System.out.println(MESSAGE_SAVING_TASKS);
        System.out.println(SEPARATOR);
    }

    public static void printIOException(Exception e) {
        System.out.println(EXCEPTION_IO + e.getMessage());
        System.out.println(SEPARATOR);
    }

    public static void printFoundTasks(TaskList listOfMatches) {
        System.out.println(MESSAGE_FIND_TASKS);
        System.out.println(listOfMatches.toString());
        System.out.println(SEPARATOR);
    }

    public static void printTaskList(TaskList tasks) {
        System.out.println(MESSAGE_LIST_HEADER);
        System.out.println(tasks.toString());
        System.out.println(SEPARATOR);
    }

    public static void printTaskDone(Task task) {
        System.out.print(MESSAGE_TASK_DONE);
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    public static void printTaskUndone(Task task) {
        System.out.print(MESSAGE_TASK_UNDONE);
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    public static void printTaskAdded(Task task, int numTasks) {
        System.out.println(MESSAGE_TASK_ADDED + task.toString());
        String singularOrPlural = numTasks == 1 ? " task." : " tasks.";
        System.out.println(MESSAGE_CURRENT_NUMTASKS + numTasks + singularOrPlural);
        System.out.println(SEPARATOR);
    }

    public static void printTaskDeleted(Task task) {
        System.out.println(MESSAGE_TASK_DELETED + task.toString());
        System.out.println(SEPARATOR);
    }

    public static void printInvalidTaskNo() {
        System.out.println(EXCEPTION_INVALID_TASK_NUMBER);
        System.out.println(SEPARATOR);
    }

    public static void printNoValueForFields() {
        System.out.println(EXCEPTION_NO_VALUE_FOR_REQUIRED_FIELDS);
        System.out.println(SEPARATOR);
    }

    public static void printNoSuchCommand() {
        System.out.println(EXCEPTION_NO_SUCH_COMMAND);
        System.out.println(SEPARATOR);
    }

    public static void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(SEPARATOR);
    }

    public static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine.trim();
    }
}
