package humi;

public class Ui {
    public static final String LINE = "    ____________________________________________________________";

    /**
     * Print starting message when the program starts
     */
    public void printWelcome() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm Humi");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Print exit message when the program terminates
     */
    public void printExit() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Print message when todo task has been successfully added to the list
     * @param description Description of the todo task
     */
    public static void printAddTodo(String description) {
        System.out.println(Ui.LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [T][ ] " + description);
        System.out.println("     Now you have " + (TaskManager.taskCount + 1) + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    /**
     * Print message when deadline task has been successfully added to the list
     * @param description Description of the deadline task
     * @param deadline Due date of the deadline task
     */
    public static void printAddDeadline(String description, String deadline) {
        System.out.println(Ui.LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [D][ ] " + description + " by: " + deadline);
        System.out.println("     Now you have " + (TaskManager.taskCount + 1) + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    /**
     * Print message when event task has been successfully added to the list
     * @param description Description of the event task
     * @param startDate Starting date of the event task
     * @param endDate End date of the event task
     */
    public static void printAddEvent(String description, String startDate, String endDate) {
        System.out.println(Ui.LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [E][ ] " + description + " from: " + startDate + " to: " + endDate);
        System.out.println("     Now you have " + (TaskManager.taskCount + 1) + " tasks in the list.");
        System.out.println(Ui.LINE);
    }
}
