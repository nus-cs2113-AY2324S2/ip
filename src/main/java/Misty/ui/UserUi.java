package misty.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import misty.data.task.Task;

/**
 * Handles interaction between user and chatbot.
 */
public class UserUi {
    String userInput;
    Scanner in = new Scanner(System.in);

    /**
     * Constructs UserUi object.
     */
    public UserUi() {
    }

    /**
     * Returns input provided by user.
     *
     * @return User input as a string.
     */
    public String getUserCommand() {
        userInput = in.nextLine();
        return userInput;
    }

    /**
     * Prints border used to demarcate messages.
     */
    public void printMessageBorder() {
        System.out.println("\t--------------------------------------------------");
    }

    /**
     * Prints string to screen with message border at the start and end of message.
     *
     * @param string String to be printed with border.
     */
    public void printMessageWithBorder(String string) {
        printMessageBorder();
        System.out.println(string);
        printMessageBorder();
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        String welcome = "\tHello! I'm Misty\n"
                + "\tWhat can I do for you?";
        printMessageWithBorder(welcome);
    }

    /**
     * Prints bye message.
     */
    public void printByeMessage() {
        System.out.println("\tBye! Hope to see you again soon!");
    }

    /**
     * Prints unknown command message.
     */
    public void printUnknownCommandMessage() {
        String bye = "Sorry, unknown command entered!";
        System.out.println("\t" + bye);
    }

    /**
     * Prints number of task in task list.
     *
     * @param taskCount Number of task in task list.
     */
    public void printTaskCount(int taskCount) {
        System.out.println(String.format("\tYou now have %d tasks in the list.", taskCount));
    }

    /**
     * Prints task added to task list message.
     * @param newTask Task which was added to taks list.
     */
    public void printAddTaskMessage(Task newTask) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t\t%s", newTask));
    }

    /**
     * Prints task marked message.
     *
     * @param task Task which was marked.
     */
    public void printTaskMarkAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println(String.format("\t%s",task));
    }

    /**
     * Prints task unmarked message.
     *
     * @param task Task which was unmarked.
     */
    public void printTaskUnmarkAsNotDone(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s",task));
    }

    /**
     * Prints task deleted message.
     *
     * @param task Task which was deleted.
     */
    public void printDeleteTask(Task task) {
        System.out.println("\tAlright, I've deleted this task:");
        System.out.println(String.format("\t%s", task));
    }

    /**
     * Prints all tasks in task list.
     *
     * @param list ArrayList containing all tasks.
     */
    public void printList(ArrayList<Task> list) {
        Integer itemNumber = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task task : list) {
            System.out.println(String.format("\t%d.%s", itemNumber, task));
            itemNumber++;
        }
    }

    /**
     * Prints tasks occurring on a specified date.
     *
     * @param localDate Date to be compared to tasks.
     */
    public void printCheckMessage(LocalDate localDate) {
        System.out.println(String.format("\tHere are the tasks occurring on %s:",
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
    }

    /**
     * Prints find task message.
     */
    public void printFindTask() {
        System.out.println("\tHere are the matching tasks in the list:");
    }

    /**
     * Prints error that empty parameters were given.
     */
    public void printErrorEmptyParameter() {
        System.out.println("\tEmpty parameters detected! Please fill in all fields");
    }

    /**
     * Prints error that invalid format was detected.
     */
    public void printErrorInvalidFormat() {
        System.out.println("\tInvalid Format! Please enter again");
    }

    /**
     * Prints error that invalid ID was given.
     */
    public void printErrorInvalidId() {
        System.out.println("\tInvalid ID! Please enter again");
    }

    /**
     * Prints that IO error was detected.
     */
    public void printErrorIO() {
        System.out.println("------WARNING! I/O Error detected. Unable to save changes!------");
    }

    /**
     * Prints that security error was detected.
     */
    public void printErrorSecurity() {
        System.out.println("------WARNING! File access denied. Unable to save changes!------");
    }

    /**
     * Prints error that file was not found.
     */
    public void printErrorFileNotFound() {
        System.out.println("------WARNING! Data file not found. Unable to load!------");
    }

    /**
     * Prints error that save file data is in incorrect format.
     */
    public void printErrorCorruptedFile() {
        System.out.println("------WARNING! Data file corrupted. Unable to load!------");
    }

    /**
     * Prints error that date provided is in incorrect format.
     */
    public void printErrorInvalidDateFormat() {
        System.out.println("\tDate provided not in correct format! Please enter again");
    }

    /**
     * Prints usage of todo command.
     */
    public void printUsageUsageTodo() {
        System.out.println("\tTodo command syntax: todo <task name>");
    }

    /**
     * Prints usage of deadline command.
     */
    public void printUsageDeadline() {
        System.out.println("\tDeadline command syntax: deadline <task name> /by <deadline>");
    }

    /**
     * Prints usage of event command.
     */
    public void printUsageEvent() {
        System.out.println("\tEvent command syntax: Event <task name> /from <event start> /to <event end>");
    }

    /**
     * Prints usage of mark command.
     */
    public void printUsageMark() {
        System.out.println("\tMark command syntax: mark <task id>");
    }

    /**
     * Prints usage of unmark command.
     */
    public void printUsageUnmark() {
        System.out.println("\tUnmark command syntax: unmark <task id>");
    }

    /**
     * Prints usage of delete command.
     */
    public void printUsageDelete() {
        System.out.println("\tDelete command syntax: delete <task id>");
    }

    /**
     * Prints usage of check command.
     */
    public void printUsageCheck() {
        System.out.println("\tCheck command syntax: check <date in yyyy-mm-dd format>");
    }

    /**
     * Prints usage of find command.
     */
    public void printUsageFind() {
        System.out.println("\tFind command syntax: find <keyword>");
    }
}