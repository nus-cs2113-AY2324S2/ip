import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
        printHello();
    }

    /**
     * Prints a hello message.
     */
    public static void printHello() {
        printDivider();
        System.out.println("Hello! I'm BOOP");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a horizontal line.
     */
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Loads user input and splits array.
     *
     * @return an array containing the command split up
     */
    public String[] getUserInput() {
        printDivider();
        String command = this.scanner.nextLine();
        String[] comArr = command.split(" ");
        comArr[0] = comArr[0].toLowerCase();
        return comArr;
    }

    /**
     * Prints an error message for invalid user input.
     */
    public static void printInputError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDivider();
    }

    /**
     * Prints a bye message.
     */
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints the number of tasks in the task list.
     *
     * @param num the number of tasks
     */
    public static void printNumTasks(int num) {
        System.out.println("You currently have " + num + " tasks on your list");
        printDivider();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasklist the TaskList object
     */
    public static void printTaskList(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for(Task t: tasklist.getTasks()) {
            System.out.println("" + count + ". " + t.toString());
            count += 1;
        }
        printDivider();
    }

    /**
     * Prints an error message for incorrect format for mark, unmark, delete
     *
     * @param command the command type (mark, unmark, delete)
     */
    public static void markDeleteFormatError(String command) {
        System.out.println("Please provide one task number in this list to " + command);
        printDivider();
    }

    /**
     * Prints an error message for unknown commands.
     */
    public static void unknownCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDivider();
    }

    /**
     * Prints an error message for incorrect format when adding a task.
     *
     * @param taskType the type of task (todo, deadline, or event)
     */
    public static void taskFormatError(String taskType) {
        if (taskType.equals("todo")) {
            System.out.println("OOPS!!! The format of your input is incorrect");
            System.out.println("correct format: todo <description>");
        } else if (taskType.equals("deadline")) {
            System.out.println("OOPS!!! The format of your input is incorrect");
            System.out.println("correct format: deadline <description> /by <date>");
        } else {
            System.out.println("OOPS!!! The format of your input is incorrect");
            System.out.println("correct format: event <description> /from <start date> /to <end date>");
        }
        printDivider();
    }

    /**
     * Prints a message that confirms addition of a task.
     *
     * @param num the number of tasks
     * @param t   the task added
     */
    public static void printAddTask(int num, Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + num + " tasks in the list.");
        printDivider();
    }

    /**
     * Prints an error message.
     *
     * @param error the error message to be printed
     */
    public static void printError(String error) {
        System.out.println(error);
        printDivider();
    }

    /**
     * Prints an error message for incorrect format for find command.
     */
    public static void findError() {
        System.out.println("OOPS!!! The format of your input is incorrect");
        printDivider();
    }

    /**
     * Prints the list of matching tasks found during search.
     *
     * @param found the list of matching tasks
     */
    public static void printFound(ArrayList<Task> found) {
        if(found.size() == 0) {
            System.out.println("No tasks in your list match");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for(Task t: found) {
                System.out.println(t.toString());
            }
        }
        printDivider();
    }
}
