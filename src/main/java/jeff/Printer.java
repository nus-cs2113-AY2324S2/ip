package jeff;

public class Printer {
    public static void printIndent(String s) {
        System.out.println("     " + s);
    }

    public static void printDivider() {
        String divider = "    ____________________________________________________________";
        System.out.println(divider);
    }

    public static void printWelcome() {
        printDivider();
        printIndent("Hello! I'm Jeff");
        printIndent("What can I do for you?");
        printDivider();
    }

    public static void printBye() {
        printIndent("Bye. Hope to see you again soon!");
    }

    public static void printTasks() {
        printIndent("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.size(); i++) {
            printIndent((i + 1) + "." + TaskList.get(i));
        }
    }

    public static void printAddTask() {
        printIndent("Got it. I've added this task:");
        printIndent("  " + TaskList.back());
        printIndent("Now you have " + TaskList.size() + " tasks in the list.");
    }

    public static void printDeleteTask(Task deletedTask) {
        printIndent("Noted. I've removed this task:");
        printIndent("  " + deletedTask);
        printIndent("Now you have " + TaskList.size() + " tasks in the list.");
    }

    public static void printMarkTask(Task currentTask) {
        printIndent("Nice! I've marked this task as done:");
        printIndent("  " + currentTask);
    }

    public static void printUnmarkTask(Task currentTask) {
        printIndent("OK, I've marked this task as not done yet:");
        printIndent("  " + currentTask);
    }

    public static void printUnableToMark() {
        printIndent("Unable to mark any tasks as task list is empty.");
    }

    public static void printUnableToUnmark() {
        printIndent("Unable to unmark any tasks as task list is empty.");
    }

    public static void printUnableToDelete() {
        printIndent("Unable to delete any tasks as task list is empty.");
    }
}
