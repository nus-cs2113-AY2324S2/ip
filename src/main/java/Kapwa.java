import java.util.Scanner;

public class Kapwa {
    private static final int MAX_TASKS = 100;
    private static final String DIVIDER_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        String inputLine;
        System.out.println("What can I do for you?\n" + DIVIDER_LINE);

        do {
            inputLine = scanner.nextLine();
            System.out.println(DIVIDER_LINE);
            if (!inputLine.equals("bye")) {
                addTask(tasks, inputLine, taskCount); // Add task or display task list
                if (!inputLine.equals("list") && !inputLine.startsWith("mark ") && !inputLine.startsWith("unmark ")) {
                    taskCount++;
                }
            }
            System.out.println(DIVIDER_LINE);
        } while (!inputLine.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        String logo = " _  __    _    ____  __        __    _    _ \n"
                + "| |/ /   / \\  |  _ \\ \\ \\      / /   / \\  | |\n"
                + "| ' /   / _ \\ | |_) | \\ \\ /\\ / /   / _ \\ | |\n"
                + "| . \\  / ___ \\|  __/   \\ V  V /   / ___ \\|_|\n"
                + "|_|\\_\\/_/   \\_\\_|       \\_/\\_/   /_/   \\_(_)\n";

        System.out.println("Hello from Duke\n" + logo +
                "Hello! I'm Kapwa\n" +
                "What can I do for you?\n" +
                DIVIDER_LINE);
    }

    private static void displayTaskList(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(taskCount + ". " + tasks[i]);
        }
    }

    private static void markTaskAsDone(Task[] tasks, String inputLine, int taskCount) {
        int taskNumber = Integer.parseInt(inputLine.substring(5)) - 1;
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber]);
        } else {
            System.out.println("Task number is invalid.");
        }
    }

    private static void markTaskAsNotDone(Task[] tasks, String inputLine, int taskCount) {
        int taskNumber = Integer.parseInt(inputLine.substring(7)) - 1;
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber]);
        } else {
            System.out.println("Task number is invalid.");
        }
    }

    private static void addTask(Task[] tasks, String inputLine, int taskCount) {
        if (inputLine.equals("list")) {
            displayTaskList(tasks, taskCount);
        } else if (inputLine.startsWith("mark ")) {
            markTaskAsDone(tasks, inputLine, taskCount);
        } else if (inputLine.startsWith("unmark ")) {
            markTaskAsNotDone(tasks, inputLine, taskCount);
        } else {
            String taskType = inputLine.split(" ", 2)[0];
            Task newTask = null;
            switch (taskType) {
            case "todo":
                newTask = new Todo(inputLine.substring(5).trim());
                break;
            case "deadline":
                String[] deadlineParts = inputLine.substring(9).trim().split("/by", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("Error: Invalid deadline format.");
                    return;
                }
                newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                break;
            case "event":
                String[] eventParts = inputLine.substring(6).trim().split("/from", 2);
                String[] eventTimeParts = eventParts.length > 1 ? eventParts[1].trim().split("/to", 2)
                        : new String[0];
                if (eventTimeParts.length < 2) {
                    System.out.println("Error: Invalid event format.");
                    return;
                }
                newTask = new Event(eventParts[0].trim(), eventTimeParts[0].trim(), eventTimeParts[1].trim());                   
                break;
                
            default:
                newTask = new Task(inputLine);
            break;
            }
            if (newTask != null) {
                tasks[taskCount] = newTask;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
            }
        }
    }
}
