import java.util.Scanner;

public class Quokka {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("    added: " + task);
        } else {
            System.out.println("    Sorry, the task list is full. You cannot add more tasks.");
        }
    }

    private static void displayTasks() {
        if (taskCount == 0) {
            System.out.println("    No tasks added yet.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + ". " + tasks[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Quokka");
        System.out.println("What can I do for you?");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the user wants to list tasks
            if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                // Add Task based on input
                addTask(userInput);
            }

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }
}
