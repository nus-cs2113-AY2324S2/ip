import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static final String DIVIDER_LINE = "____________________________________________________________";
    
    public static void main(String[] args) {
        // Display the welcome message
        displayWelcomeMessage();
        
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;
        
        String inputLine;
        System.out.println("What can I do for you?\n" + DIVIDER_LINE);

        do {
            inputLine = scanner.nextLine();
            System.out.println(DIVIDER_LINE);
            if (inputLine.equals("list")) {
                displayTaskList(tasks, taskCount);
            } else if (inputLine.startsWith("mark ")) {
                markTaskAsDone(tasks, inputLine, taskCount);
            } else if (inputLine.startsWith("unmark ")) {
                markTaskAsNotDone(tasks, inputLine, taskCount);
            } else if (!inputLine.equals("bye")) {
                addTask(tasks, inputLine, taskCount);
                taskCount++;
            }
            System.out.println(DIVIDER_LINE);
        } while (!inputLine.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        String logo = " ____  _    _  ____ _____ ___  ____  \n"
                + "|  _ \\| |  | |/ ___|_   _/ _ \\|  _ \\ \n"
                + "| | | | |  | | |     | || | | | | | |\n"
                + "| |_| | |__| | |___  | || |_| | |_| |\n"
                + "|____/ \\____/ \\____| |_| \\___/|____/ \n";
        System.out.println("Hello from Duke\n" + logo +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                DIVIDER_LINE);
    }

    private static void displayTaskList(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            String statusIcon = tasks[i].isDone ? "X" : " "; // Mark done tasks with X
            System.out.println((i + 1) + ".[" + statusIcon + "] " + tasks[i].description);
        }
    }

    private static void markTaskAsDone(Task[] tasks, String inputLine, int taskCount) {
        int taskNumber = Integer.parseInt(inputLine.substring(5)) - 1;
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber].description);
        } else {
            System.out.println("Task number is invalid.");
        }
    }

    private static void markTaskAsNotDone(Task[] tasks, String inputLine, int taskCount) {
        int taskNumber = Integer.parseInt(inputLine.substring(7)) - 1;
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber].description);
        } else {
            System.out.println("Task number is invalid.");
        }
    }

    private static void addTask(Task[] tasks, String inputLine, int taskCount) {
        tasks[taskCount] = new Task(inputLine);
        System.out.println("added: " + tasks[taskCount].description);
    }
}
