import java.util.Scanner;

public class UserInterface {
    private static final String LINE = "-----------------------------------------";
    private static final String TAB_SPACE = "    ";
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

    public void printTaskAdded(Task task, int index, int totalTasks) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I've added this task:");
        System.out.println(TAB_SPACE + "  " + task);
        System.out.println(TAB_SPACE + "Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printTaskList(Task[] taskList, int index) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println(TAB_SPACE + (i + 1) + "." + taskList[i]);
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

    public void printInvalidTaskIndex(int index) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid task index for marking/unmarking. Please check your input.");
        System.out.println(LINE);
    }

    public void sayGoodbye() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void printInvalidEventFormat() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE
                + "Invalid event format. Please use: event [description] /from [start date/time] /to [end date/time]");
        System.out.println(LINE);
    }

    public void printInvalidDeadlineFormat() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid deadline format. Please use: deadline [description] /by [date/time]");
        System.out.println(LINE);
    }

    public void printInvalidTaskType(String taskDescription) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid task type: " + taskDescription);
        System.out.println(TAB_SPACE + "Please mention the task type (todo, deadline, or event)");
        System.out.println(LINE);
    }

    public void printInvalidTodoFormat() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid todo format. Please dont put empty description");
        System.out.println(LINE);
    }
}
