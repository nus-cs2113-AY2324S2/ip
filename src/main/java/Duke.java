import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // ArrayList to store Task objects
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Initial greeting to the user
        System.out.println("  " + Constants.LOGO);
        System.out.println("  " + Constants.LINE);
        System.out.println("  Hello! I'm Duke\n  What can I do for you?");
        System.out.println("  " + Constants.LINE);

        // Scanner to read user input from the console
        Scanner scanner = new Scanner(System.in);
        String input;

        // Main loop to process user commands
        while (true) {
            input = scanner.nextLine();
            System.out.println("  " + Constants.LINE);

            // Condition to check if the user wants to exit the chatbot
            if ("bye".equalsIgnoreCase(input)) {
                System.out.println("  Bye. Hope to see you again soon!");
                System.out.println("  " + Constants.LINE);
                break;
            }
            // Condition to check if the user wants to list all tasks
            else if ("list".equalsIgnoreCase(input)) {
                listTasks();
            }
            // Condition to check if the user wants to mark a task as done
            else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; // Parse the task number
                markTask(taskNumber, true); // Mark the task as done
            }
            // Condition to check if the user wants to unmark a task
            else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; // Parse the task number
                markTask(taskNumber, false); // Mark the task as not done
            }
            // Any other input is considered as adding a new task
            else {
                addTask(input);
            }
            System.out.println("  " + Constants.LINE);
        }

        // Close the scanner when we're done to prevent resource leaks
        scanner.close();
    }

    // Method to add a task to the list
    private static void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("  added: " + description);
    }

    // Method to print out all tasks with their completion status
    private static void listTasks() {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i));
        }
    }

    // Method to mark a task as done or not done
    private static void markTask(int taskIndex, boolean isDone) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) { // Check if the index is valid
            Task task = tasks.get(taskIndex);
            if (isDone) { // If marking as done
                task.markAsDone();
                System.out.println("  Nice! I've marked this task as done:");
            } else { // If marking as not done
                task.markAsNotDone();
                System.out.println("  OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + task); // Print the task's status
        } else {
            System.out.println("  Task number is invalid."); // Inform user if the index is invalid
        }
    }
}