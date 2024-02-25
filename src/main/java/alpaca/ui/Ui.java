package alpaca.ui;

import alpaca.tasks.Task;
import alpaca.tasks.TaskList;

public class Ui {
    private static final String LOGO = "           _      _____        _____          \n" +
        "     /\\   | |    |  __ \\ /\\   / ____|   /\\    \n" +
        "    /  \\  | |    | |__) /  \\ | |       /  \\   \n" +
        "   / /\\ \\ | |    |  ___/ /\\ \\| |      / /\\ \\  \n" +
        "  / ____ \\| |____| |  / ____ \\ |____ / ____ \\ \n" +
        " /_/    \\_\\______|_| /_/    \\_\\_____/_/    \\_\\\n" +
        "                                              \n" +
        "                                              ";

    private static final String HORIZONTAL_LINE = "_____________" +
        "_______________________________________________\n";

    private static final String GREET_MESSAGE = "Baa-baa-baa, I'm Alpaca!\n"
        + "How can I assist you today?\n";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon, baa-baa-baa!\n";

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }
    public void printGreeting() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GREET_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        System.out.print(taskList.listTasks());
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAddTask(Task task, int listCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printInvalidIndex() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Invalid index");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println(HORIZONTAL_LINE);
    }
}
