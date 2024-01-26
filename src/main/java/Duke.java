import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

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
