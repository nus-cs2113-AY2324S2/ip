package Misty;

import Misty.Task.Task;
import java.util.ArrayList;

public class Parser {
    public static void printMessageBorder() {
        System.out.println("\t--------------------------------------------------");
    }

    public static void printMessageWithBorder(String string) {
        printMessageBorder();
        System.out.println(string);
        printMessageBorder();
    }

    public static void printWelcomeMessage() {
        String welcome = "\tHello! I'm Misty\n"
                + "\tWhat can I do for you?";

        printMessageWithBorder(welcome);
    }

    public static void printByeMessage() {
        System.out.println("\tBye! Hope to see you again soon!");
    }

    public static void printUnknownCommandMessage() {
        String bye = "Sorry, unknown command entered!";
        System.out.println("\t" + bye);
    }

    public static void printTaskCount(int taskCount) {
        System.out.println(String.format("\tYou now have %d tasks in the list.", taskCount));
    }

    public static void printAddTaskMessage(Task newTask) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t\t%s", newTask));
    }

    public static void printTaskMarkAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println(String.format("\t%s",task));
    }

    public static void printTaskUnmarkAsNotDone(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s",task));
    }

    public static void printDeleteTask(Task task) {
        System.out.println("\tAlright, I've deleted this task:");
        System.out.println(String.format("\t%s", task));
    }

    public static void printList(ArrayList<Task> list, int itemCount) {
        Integer itemNumber = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task task : list) {
            System.out.println(String.format("\t%d.%s", itemNumber, task));
            itemNumber++;
        }
    }

    public static void printErrorMissingParam() {
        System.out.println("\tIncorrect format, there are missing parameters");
    }
    public static void printErrorNoTaskName() {
        System.out.println("\tPlease enter name of task");
    }

    public static void printErrorNoBy() {
        System.out.println("\tPlease enter when task is due (/by)");
    }

    public static void printErrorNoFrom() {
        System.out.println("\tPlease enter when the event starts (/from)");
    }

    public static void printErrorNoTo() {
        System.out.println("\tPlease enter when the event ends (/to)");
    }

    public static void printErrorInvalidFormat() {
        System.out.println("\tInvalid Format! Please enter again");
    }

    public static void printErrorNoId() {
        System.out.println("\tPlease enter a task ID");
    }

    public static void printErrorInvalidId() {
        System.out.println("\tPlease enter a valid task id");
    }

    public static void printUsageUsageTodo() {
        System.out.println("\tTodo command syntax: todo <task name>");
    }

    public static void printUsageDeadline() {
        System.out.println("\tDeadline commnad syntax: deadline <task name> /by <deadline>");
    }

    public static void printUsageEvent() {
        System.out.println("\tEvent command syntax: Event <task name> /from <event start> /to <event end>");
    }

    public static void printUsageMark() {
        System.out.println("\tMark command syntax: mark <task id>");
    }

    public static void printUsageUnmark() {
        System.out.println("\tUnmark command syntax: unmark <task id>");
    }

    public static void printUsageDelete() {
        System.out.println("\tDelete command syntax: delete <task id>");
    }
}
