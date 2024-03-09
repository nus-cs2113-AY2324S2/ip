import java.util.Scanner;

public class Hailey {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine().trim().toLowerCase();
            printLine();

            if (userInput.equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                printTasks();
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark ")) {
                unmarkTask(userInput);
            } else {
                addTask(userInput);
            }
        }

        printGoodbyeMessage();
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

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = new Task(taskDescription);
            System.out.println("added: " + taskDescription);
        } else {
            System.out.println("Sorry, task list is full!");
        }
        printLine();
    }

    private static void markTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number!");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format!");
        }
        printLine();
    }

    private static void unmarkTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number!");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format!");
        }
        printLine();
    }

    private static void printTasks() {
        if (taskCount == 0) {
            System.out.println("Task list is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        printLine();
    }

    private static boolean isValidTaskIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}