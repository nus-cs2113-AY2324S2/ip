import java.util.Scanner;

public class Quokka {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            Task newTask = new Task(taskDescription);
            tasks[taskCount++] = newTask;
            System.out.println("    added: " + newTask.getDescription());
        } else {
            System.out.println("    Sorry, the task list is full. You cannot add more tasks.");
        }
    }

    private static void displayTasks() {
        if (taskCount == 0) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            }
        }
    }

    private static void markTaskAsDone(String userInput) {
        updateTaskStatus(userInput, true, "Nice! I've marked this task as done:");
    }

    private static void markTaskAsNotDone(String userInput) {
        updateTaskStatus(userInput, false, "OK, I've marked this task as not done yet:");
    }

    private static void updateTaskStatus(String userInput, boolean newStatus, String statusMessage) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 2) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].setStatus(newStatus);
                System.out.println("    " + statusMessage);
                System.out.println("      [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
            } else {
                System.out.println("    Invalid task index.");
            }
        } else {
            System.out.println("    Please provide a valid task index to mark as done or not done.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Quokka");
        System.out.println("What can I do for you?");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            }

            // Check if the user wants to list tasks
            if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                // Add Task based on input
                if (userInput.toLowerCase().startsWith("mark ")) {
                    markTaskAsDone(userInput);
                } else if (userInput.toLowerCase().startsWith("unmark ")) {
                    markTaskAsNotDone(userInput);
                } else {
                    addTask(userInput);
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}
