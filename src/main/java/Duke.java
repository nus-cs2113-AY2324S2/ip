import java.util.Scanner;

public class Duke {
    // Array to store tasks with a maximum of 100 entries.
    private static final String[] tasks = new String[100];
    // Counter for the number of tasks currently stored.
    private static int taskCount = 0;

    public static void main(String[] args) {
        // Print the welcome message with two spaces for indentation.
        System.out.println("  " + Constants.LOGO);
        System.out.println("  " + Constants.LINE);
        System.out.println("  Hello! I'm Duke\n  What can I do for you?");
        System.out.println("  " + Constants.LINE);

        // Scanner to read user input.
        Scanner scanner = new Scanner(System.in);
        String input;

        // Main command processing loop.
        while (true) {
            input = scanner.nextLine();

            // 'bye' command to exit the program.
            if ("bye".equalsIgnoreCase(input)) {
                System.out.println("  " + Constants.LINE);
                System.out.println("  Bye. Hope to see you again soon!");
                System.out.println("  " + Constants.LINE);
                break;
            }
            // 'list' command to display stored tasks.
            else if ("list".equalsIgnoreCase(input)) {
                System.out.println("  " + Constants.LINE);
                listTasks();
                System.out.println("  " + Constants.LINE);
            }
            // Any other input is considered a task and added to the list.
            else {
                System.out.println("  " + Constants.LINE);
                addTask(input);
                System.out.println("  " + Constants.LINE);
            }
        }

        scanner.close();
    }

    // Add a task to the array and increment task counter.
    private static void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("  added: " + task);
        } else {
            System.out.println("  Task list is full!");
        }
    }

    // Print all tasks with numbering.
    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println("  " + (i + 1) + ". " + tasks[i]);
        }
    }
}
