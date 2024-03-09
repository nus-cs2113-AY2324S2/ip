import java.util.Scanner;

public class Hailey {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine().trim();
            printLine();

            if (userInput.equalsIgnoreCase("bye")) {
                printGoodbyeMessage();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printTasks();
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "What can I help you with？");
        printLine();
        System.out.println("Hello! I'm Hailey\nWhat can I do for you?");
        printLine();
    }

    private static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("added: " + task);
        } else {
            System.out.println("Sorry, task list is full!");
        }
        printLine();
    }

    private static void printTasks() {
        if (taskCount == 0) {
            System.out.println("Task list is empty!");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        printLine();
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}

