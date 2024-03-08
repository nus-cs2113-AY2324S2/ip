package schmidt.ui;

import schmidt.task.Task;
import schmidt.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "------------------------------------------------------------";
    private static final String LOGO = "░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗\n" +
            "██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝\n" +
            "╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░\n" +
            "░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░\n" +
            "██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░\n" +
            "╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░";
    private static final String HELP_MESSAGE = "I'm sorry, but the valid commands are:\n" +
            "\tbye\n" +
            "\tlist\n" +
            "\ttodo <description>\n" +
            "\tdeadline <description> /by <date>\n" +
            "\tevent <description> /from <date> /to <date>\n" +
            "\tmark <task number>\n" +
            "\tunmark <task number>\n" +
            "\tdelete <task number>";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWithLines(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        System.out.print("\t-> ");
        return scanner.nextLine();
    }

    // Include other methods like printWelcome(), printGoodbye(), printError(), printTaskList(), etc.
    public void printWelcome() {
        printMessage(LOGO);
        printWithLines("Hello! I'm Schmidt\nWhat can I do for you?");
    }

    public void printGoodbye() {
        printWithLines("Goodbye! Hope to see you again soon!");
    }

    public void printError(String errorMessage) {
        printWithLines(errorMessage);
    }

    public void printTaskList(TaskList tasks) {
        printWithLines(tasks.toString());
    }

    public void printTaskAdded(Task task, int taskCount) {
        printWithLines("Got it. I've added this task:\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.");
    }

    public void printTaskDeleted(Task task, int taskCount) {
        printWithLines("Noted. I've removed this task:\n\t" + task + "\nNow you have " + taskCount + " tasks in the list.");
    }

    public void printTaskDone(Task task) {
        printWithLines("Nice! I've marked this task as done:\n\t" + task);
    }

    public void printTaskUndone(Task task) {
        printWithLines("Ok! I've unmarked this task as done:\n\t" + task);
    }

    public void printMatchingTasks(TaskList tasks) {
        printWithLines("Here are the matching tasks in your list:\n" + tasks);
    }

    public void printHelpMessage() {
        printWithLines(HELP_MESSAGE);
    }
}