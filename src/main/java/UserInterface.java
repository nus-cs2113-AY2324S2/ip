import Exceptions.InvalidDeadlineFormatException;
import Exceptions.InvalidEventFormatException;
import Exceptions.InvalidTodoFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static final String LINE = "-----------------------------------------";
    private static final String TAB_SPACE = "    ";
    public static final int INDEX_OFFSET = 1;
    public static final int START_INDEX = 0;
    private static final String CHATBOT_NAME = "ZORO";

    public void greetUser() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Hello I'm " + CHATBOT_NAME);
        System.out.println(TAB_SPACE + "What can I do for you?");
        System.out.println(LINE);
    }

    public static String getUserInput(Scanner in) {
        return in.nextLine().trim();
    }

    public void printTaskAdded(Task task, int totalTasks) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I've added this task:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(TAB_SPACE + "Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printTaskRemoved(String task, int totalTasks) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Noted. I've removed this task:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(TAB_SPACE + "Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printTaskList(ArrayList<Task> taskList, int index) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Here are the tasks in your list:");
        for (int i = START_INDEX; i < index; i++) {
            System.out.println(TAB_SPACE + (i + INDEX_OFFSET) + "." + taskList.get(i));
        }
        System.out.println(LINE);
    }

    public void printTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Nice! I've marked this task as done:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(LINE);
    }

    public void printTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "OK, I've marked this task as not done yet:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(LINE);
    }

    public void printInvalidTaskIndex(IndexOutOfBoundsException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage() + ".\n" + TAB_SPACE + "Please check your input.");
        System.out.println(LINE);
    }

    public void sayGoodbye() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void printInvalidEventFormat(InvalidEventFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage()
                + "Please use: event [description] /from [start date/time] /to [end date/time]");
        System.out.println(LINE);
    }

    public void printInvalidDeadlineFormat(InvalidDeadlineFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage() + "Please use: deadline [description] /by [date/time]");
        System.out.println(LINE);
    }

    public void printInvalidTaskType(String taskDescription) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid task type: " + taskDescription);
        System.out.println(TAB_SPACE + "Please mention the task type (todo, deadline, or event) or \n"
                + TAB_SPACE + "use commands (list, mark, unmark, bye)");
        System.out.println(LINE);
    }

    public void printInvalidTodoFormat(InvalidTodoFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + e.getMessage() + "Please dont put empty description");
        System.out.println(LINE);
    }

    public void printTaskAlreadyMarked(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "NOTE: " + message);
        System.out.println(LINE);
    }

    public void printTaskAlreadyUnmarked(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "NOTE: " + message);
        System.out.println(LINE);
    }

    public void printInvalidInputIndex(NumberFormatException e) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid task index: " + e.getMessage());
        System.out.println(TAB_SPACE + "Please enter a valid task index to mark or unmark.");
        System.out.println(LINE);
    }

}
