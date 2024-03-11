import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
        printHello();
    }

    public static void printHello() {
        printDivider();
        System.out.println("Hello! I'm BOOP");
        System.out.println("What can I do for you?");
    }

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public String[] getUserInput() {
        printDivider();
        String command = this.scanner.nextLine();
        String[] comArr = command.split(" ");
        comArr[0] = comArr[0].toLowerCase();
        return comArr;
    }

    public static void printInputError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDivider();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printNumTasks(int num) {
        System.out.println("You currently have " + num + " tasks on your list");
        printDivider();
    }

    public static void printTaskList(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for(Task t: tasklist.getTasks()) {
            System.out.println("" + count + ". " + t.toString());
            count += 1;
        }
        printDivider();
    }

    public static void markDeleteFormatError(String command) {
        System.out.println("Please provide one task number in this list to " + command);
        printDivider();
    }

    public static void unknownCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDivider();
    }

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

    public static void printAddTask(int num, Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + num + " tasks in the list.");
        printDivider();
    }

    public static void printError(String error) {
        System.out.println(error);
        printDivider();
    }

    public static void findError() {
        System.out.println("OOPS!!! The format of your input is incorrect");
        printDivider();
    }

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
