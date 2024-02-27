package misty.ui;

import misty.data.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class UserUi {
    String userInput;
    Scanner in = new Scanner(System.in);

    public String getUserCommand() {
        userInput = in.nextLine();
        return userInput;
    }

    public void printMessageBorder() {
        System.out.println("\t--------------------------------------------------");
    }

    public void printMessageWithBorder(String string) {
        printMessageBorder();
        System.out.println(string);
        printMessageBorder();
    }

    public void printWelcomeMessage() {
        String welcome = "\tHello! I'm Misty\n"
                + "\tWhat can I do for you?";
        printMessageWithBorder(welcome);
    }

    public void printByeMessage() {
        System.out.println("\tBye! Hope to see you again soon!");
    }

    public void printUnknownCommandMessage() {
        String bye = "Sorry, unknown command entered!";
        System.out.println("\t" + bye);
    }

    public void printTaskCount(int taskCount) {
        System.out.println(String.format("\tYou now have %d tasks in the list.", taskCount));
    }

    public void printAddTaskMessage(Task newTask) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t\t%s", newTask));
    }

    public void printTaskMarkAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println(String.format("\t%s",task));
    }

    public void printTaskUnmarkAsNotDone(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s",task));
    }

    public void printDeleteTask(Task task) {
        System.out.println("\tAlright, I've deleted this task:");
        System.out.println(String.format("\t%s", task));
    }

    public void printList(ArrayList<Task> list, int itemCount) {
        Integer itemNumber = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task task : list) {
            System.out.println(String.format("\t%d.%s", itemNumber, task));
            itemNumber++;
        }
    }

    public void printErrorEmptyParameter() {
        System.out.println("\tEmpty parameters detected! Please check usage above fill in all fields");
    }

    public void printErrorInvalidFormat() {
        System.out.println("\tInvalid Format! Please check usage above and enter again");
    }

    public void printErrorInvalidId() {
        System.out.println("\tInvalid ID! Please check task id and enter again");
    }

    public void printErrorIO() {
        System.out.println("------WARNING! I/O Error detected. Unable to save changes to task list!------");
    }

    public void printErrorSecurity() {
        System.out.println("------WARNING! File access denied. Unable to save changes to task list!------");
    }

    public void printErrorFileNotFound() {
        System.out.println("------WARNING! Data file not found. Unable to load task list!------");
    }

    public void printErrorCorruptedFile() {
        System.out.println("------WARNING! Data file corrupted. Unable to load task list!------");
    }

    public void printUsageUsageTodo() {
        System.out.println("\tTodo command syntax: todo <task name>");
    }

    public void printUsageDeadline() {
        System.out.println("\tDeadline command syntax: deadline <task name> /by <deadline>");
    }

    public void printUsageEvent() {
        System.out.println("\tEvent command syntax: Event <task name> /from <event start> /to <event end>");
    }

    public void printUsageMark() {
        System.out.println("\tMark command syntax: mark <task id>");
    }

    public void printUsageUnmark() {
        System.out.println("\tUnmark command syntax: unmark <task id>");
    }

    public void printUsageDelete() {
        System.out.println("\tDelete command syntax: delete <task id>");
    }
}