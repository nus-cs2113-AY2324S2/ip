import java.util.ArrayList;

public class Printer {

    private static final String BOT_NAME = "Baymax";
    private static final String INDENT = "     ";
    private static final String LINE = "~------------------------------------------------------------~";
    private static final String TAGLINE = "Bala-lala...";
    private static final String ERROR_TAGLINE = "Ohh noo...";
    private static final int LAST_INDEX = 1;


    public static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println(INDENT + "Hello! I'm " + BOT_NAME + ", your personal task manager." + System.lineSeparator()
                + INDENT + "What can I do for you today? :)");
        System.out.println(LINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println(INDENT + "I hope you are satisfied with my service! Goodbye. :)");
        System.out.println(LINE);
    }

    public static void printTaskCount(ArrayList<Task> taskArrayList) {
        System.out.println(INDENT + "You have a total of " + taskArrayList.size() + " tasks now. :)");
    }

    public static void printAddedTask(ArrayList<Task> taskArrayList) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've added this task:");
        System.out.println(INDENT + taskArrayList.get(taskArrayList.size() - LAST_INDEX).toAddString());
        printTaskCount(taskArrayList);
        System.out.println(LINE);
    }

    public static void printTaskList(ArrayList<Task> taskArrayList) {
        System.out.println(LINE);
        if (taskArrayList.isEmpty()) {
            System.out.println(INDENT + ERROR_TAGLINE + " You have no tasks yet. Try adding some!");
        } else {
            System.out.println(INDENT + TAGLINE + " Displaying your tasks: ");
            for (int i = 0; i < taskArrayList.size(); i++) {
                System.out.println(INDENT + (i + 1) + ". " + taskArrayList.get(i));
            }
            System.out.println(INDENT + "You have a total of " + taskArrayList.size() + " tasks now. :)");
        }
    }

    public static void printMark(Task task) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've marked this task:");
        System.out.println(task);
    }

    public static void printUnmark(Task task) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've unmarked this task:");
        System.out.println(task);
    }

    public static void printDelete(ArrayList<Task> taskArrayList, int index) {
        System.out.println(LINE);
        System.out.println(INDENT + TAGLINE + " I've delete this task:");
        System.out.println(INDENT + taskArrayList.get(index));

    }

}
