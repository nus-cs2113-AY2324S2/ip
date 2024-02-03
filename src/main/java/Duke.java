import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) {
        String name = "Jonas";
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("안녕하세요, Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Kamxia. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark ")) {
                unmarkTask(userInput);
            } else if (userInput.startsWith("todo ")) {
                addTodoTask(userInput.substring(5));
            } else if (userInput.startsWith("deadline ")) {
                String[] split = userInput.substring(9).split(" /by ");
                AddDeadlineTask(split[0], split[1]);
            } else if (userInput.startsWith("event ")) {
                String[] split = userInput.substring(6).split(" /from | /to ");

                if (split.length >= 3) {
                    AddEventTask(split[0], split[1], split[2]);
                } else {
                    System.out.println("Invalid input format for event task.");
                }
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void displayTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("LaiLaiLai, Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private static void addTask(String description) {
        if (description.isEmpty()) {
            System.out.println("Anyhow one...write your task properly leh :(");
            return;
        }
        tasks[taskCount++] = new Task(description);
        System.out.println("____________________________________________________________");
        System.out.println("Added: " + description);
        System.out.println("____________________________________________________________");
    }

    public static void addTodoTask(String description) {
        if (description.isEmpty()) {
            System.out.println("Anyhow one...write your task properly leh :(");
            return;
        }
        tasks[taskCount++] = new Todo(description);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void AddDeadlineTask(String description, String by) {
        if (description.isEmpty() || by.isEmpty()) {
            System.out.println("Anyhow one...write your task properly leh :(");
            return;
        }
        tasks[taskCount++] = new Deadline(description, by);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void AddEventTask(String description, String start, String end) {
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            System.out.println("Anyhow one...write your task properly leh :(");
            return;
        }
        tasks[taskCount++] = new Event(description, start, end);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void markTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice one lah! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task has been chosen :(((((");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid request :((((((");
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK can, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task has been chosen :(((((");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid request :((((((");
        }
    }
}
