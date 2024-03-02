package RuntimeSupport;
import java.util.Scanner;
import Event.Task;
import Event.TaskList;
import java.util.List;

public class Ui {
    private final Scanner scanner;
    static String BREAK_LINE = "____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(BREAK_LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
        System.out.println(BREAK_LINE);
    }

    public void showWelcome() {
        System.out.println(BREAK_LINE);
        System.out.println("Hello! I'm 550W.\nWhat can I do for you?");
        System.out.println(BREAK_LINE);
    }

    public void showError(String message) {
        System.out.println(message);
        System.out.println(BREAK_LINE);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BREAK_LINE);
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Voilà! Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (size) + " tasks in the list.");
        System.out.println(BREAK_LINE);
    }

    public void markTaskDone(Task task) {
        System.out.println("X marks the spot! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(BREAK_LINE);
    }

    public void markTaskUndone(Task task) {
        System.out.println("All good! We've hit the rewind button and unmarked this task:");
        System.out.println("  " + task);
        System.out.println(BREAK_LINE);
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(BREAK_LINE);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Abracadabra! Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(BREAK_LINE);
    }

    public void showFoundResults(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Ah! There are no such tasks in my storage list!");
        } else {
            System.out.println("Here you go! Those are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(BREAK_LINE);
    }
}
