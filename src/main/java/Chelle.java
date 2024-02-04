import java.util.Scanner;

public class Chelle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Hello! I'm Chelle.\nI like to talkity talkity talk!");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Chelle: Bye! Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Chelle: ");
                displayTasks(tasks, taskCount);
            } else if (userInput.startsWith("mark")) {
                markTask(userInput, tasks, taskCount);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput, tasks, taskCount);
            } else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = new Task(userInput);
                    taskCount++;
                    System.out.println("Chelle: added: " + userInput);
                } else {
                    System.out.println("Chelle: Task limit reached. Cannot add more tasks.");
                }
            }
        }

        scanner.close();
    }

    private static void displayTasks(Task[] tasks, int count) {
        if (count == 0) {
            System.out.println("No tasks to display.");
        } else {
            for (int i = 0; i < count; i++) {
                Task task = tasks[i];
                String status = task.isDone() ? "[X]" : "[ ]";
                System.out.println((i + 1) + ". " + status + " " + task.getTaskName());
            }
        }
    }

    private static void markTask(String userInput, Task[] tasks, int taskCount) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markDone();
                    System.out.println("Chelle: Nice! I've marked this task as done:\n        [X]  " +
                            tasks[taskIndex].getTaskName());
                } else {
                    System.out.println("Chelle: Invalid task index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chelle: Invalid task index.");
            }
        } else {
            System.out.println("Chelle: Invalid command format.");
        }
    }

    private static void unmarkTask(String userInput, Task[] tasks, int taskCount) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markUndone();
                    System.out.println("Chelle: OK, I've marked this task as not done yet:\n        [ ]  " +
                            tasks[taskIndex].getTaskName());
                } else {
                    System.out.println("Chelle: Invalid task index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chelle: Invalid task index.");
            }
        } else {
            System.out.println("Chelle: Invalid command format.");
        }
    }
}
