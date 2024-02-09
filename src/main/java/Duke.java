import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    // Main method - entry point of the Duke application.
    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String input;

        // Main loop for command processing.
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

    // Processes the user input and calls the respective method.
    private static void processInput(String input) {
        System.out.println("  " + Constants.LINE);
        if (input.startsWith("list")) {
            listTasks();
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            // Extracts the task number and marks or unmarks the task.
            markTask(input, input.startsWith("mark"));
        } else {
            addTask(input);
        }
        System.out.println("  " + Constants.LINE);
    }

    // Adds a new task to the list.
    private static void addTask(String description) {
        Task newTask = new Task(description);
        TASKS.add(newTask);
        System.out.println("  added: " + description);
    }

    // Lists all tasks currently in the list.
    private static void listTasks() {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.println("  " + (i + 1) + "." + TASKS.get(i));
        }
    }

    // Marks or unmarks a task as done/not done based on the user input.
    private static void markTask(String input, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = TASKS.get(taskNumber);
            task.setDone(isDone);
            System.out.println("  Nice! I've marked this task as " + (isDone ? "done:" : "not done yet:"));
            System.out.println("  " + task);
        } catch (NumberFormatException e) {
            System.out.println("  Please enter a valid task number to mark.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Task number is invalid.");
        }
    }

    // Prints the welcome message to the user.
    private static void printWelcomeMessage() {
        System.out.println("  " + Constants.LOGO);
        System.out.println("  " + Constants.LINE);
        System.out.println("  Hello! I'm Duke\n  What can I do for you?");
        System.out.println("  " + Constants.LINE);
    }

    // Prints the goodbye message to the user.
    private static void printGoodbyeMessage() {
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println("  " + Constants.LINE);
    }
}