import java.util.Scanner;

public class MyChatBot {
    public static void main(String[] args) {
        String chatBotName = "Rose";
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        boolean[] taskStatus = new boolean[100];
        int taskCount = 0;

        System.out.println("______________");
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you today?");
        System.out.println("______________");

        while (true) {
            String userInput = scanner.nextLine().trim();
            System.out.println("______________");

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Byeeeee. Hope to see you again soon!");
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("Your task list is empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        String status = taskStatus[i] ? "[X]" : "[ ]";
                        System.out.println((i + 1) + ". " + status + " " + tasks[i]);
                    }
                }
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        taskStatus[taskIndex] = true;
                        System.out.println("Good Job ! Task marked as done: [X] " + tasks[taskIndex]);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid task number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        taskStatus[taskIndex] = false;
                        System.out.println("Oh no :( Task marked as not done: [ ] " + tasks[taskIndex]);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid task number.");
                }
            } else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = userInput;
                    taskStatus[taskCount] = false;
                    System.out.println("Task added: " + userInput);
                    taskCount++;
                } else {
                    System.out.println("Task list is full. Cannot add more tasks!!");
                }
            }
            System.out.println("______________");
        }

        scanner.close();
    }
}


