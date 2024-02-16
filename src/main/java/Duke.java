import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            if ("bye".equalsIgnoreCase(input)) {
                printGoodbyeMessage();
                break;
            }
            processInput(input);
        }
        scanner.close();
    }

    private static void processInput(String input) {
        if (input.startsWith("todo")) {
            handleTodo(input);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input);
        } else if (input.startsWith("event")) {
            handleEvent(input);
        } else if (input.startsWith("list")) {
            listTasks();
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            markOrUnmarkTask(input);
        } else {
            System.out.println("  I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("  " + Constants.LINE);
    }

    private static void handleTodo(String input) {
        String description = input.substring(5);
        Todo todo = new Todo(description);
        tasks.add(todo);
        System.out.println("  Got it. I've added this task:\n  " + todo);
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String input) {
        String[] parts = input.substring(9).split(" /by ");
        Deadline deadline = new Deadline(parts[0], parts[1]);
        tasks.add(deadline);
        System.out.println("  Got it. I've added this task:\n  " + deadline);
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String input) {
        String[] parts = input.substring(6).split(" /at ");
        Event event = new Event(parts[0], parts[1]);
        tasks.add(event);
        System.out.println("  Got it. I've added this task:\n  " + event);
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i));
        }
    }

    private static void markOrUnmarkTask(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        Task task = tasks.get(index);

        if (input.startsWith("mark")) {
            task.setDone(true);
            System.out.println("  Nice! I've marked this task as done:");
        } else {
            task.setDone(false);
            System.out.println("  OK, I've marked this task as not done yet:");
        }
        System.out.println("    " + task);
    }

    private static void printWelcomeMessage() {
        System.out.println("  " + Constants.LOGO);
        System.out.println("  " + Constants.LINE);
        System.out.println("  Hello! I'm Duke\n  What can I do for you?");
        System.out.println("  " + Constants.LINE);
    }

    private static void printGoodbyeMessage() {
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println("  " + Constants.LINE);
    }
}