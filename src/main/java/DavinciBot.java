import java.util.Arrays;
import java.util.Scanner;

/**
 * DavinciBot is a simple bot that allows the user to manage a to-do list.
 * The bot echoes commands entered by the user, adds tasks to the list,
 * marks tasks as done, and unmarks tasks.
 */
public class DavinciBot {

    /**
     * Displays the tasks in the user's to-do list.
     *
     * @param taskArray Array of tasks.
     */
    private static void displayTaskList(Task[] taskArray) {
        if (taskArray.length == 0) {
            System.out.println("No tasks entered yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskArray.length; i++) {
                String taskType = taskArray[i] instanceof Deadline ? "[D]" :
                        taskArray[i] instanceof Todo ? "[T]" :
                                taskArray[i] instanceof Event ? "[E]" : "[ ]"; // Default to "[ ]" if unknown type

                System.out.println((i + 1) + ". " + taskType +
                        " [" + taskArray[i].getStatusIcon() + "] " +
                        taskArray[i].getDescription());
            }
        }
    }





    /**
     * Marks a specified task as completed.
     *
     * @param userInput User input specifying the task.
     * @param taskArray Array of tasks.
     */
    private static void completeTask(String userInput, Task[] taskArray) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskArray.length) {
                taskArray[taskIndex].markAsDone();
                System.out.println("Nice job! I've marked this task as done :D");
                System.out.println("[" + taskArray[taskIndex].getStatusIcon() + "] " +
                        taskArray[taskIndex].getDescription());
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Please specify the task index to complete.");
        }
    }

    /**
     * Marks a specified task as not done.
     *
     * @param userInput User input specifying the task.
     * @param taskArray Array of tasks.
     */
    private static void unmarkTask(String userInput, Task[] taskArray) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskArray.length) {
                taskArray[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done:");
                System.out.println("[" + taskArray[taskIndex].getStatusIcon() + "] " +
                        taskArray[taskIndex].getDescription());
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Please specify the task index to unmark.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] taskArray = new Task[0];

        System.out.println(
                "Hello! I'm DavinciBot! I'm pleased to be of assistance. \n" +
                        "Enter commands, and I will echo them back to you, as well as add them to your to-do list. \n" +
                        "Type 'bye' to end the conversation. \n" +
                        "Type 'list' to see your to-do list. \n" +
                        "Type 'mark' to mark a task as done. \n" +
                        "Type 'unmark' to mark a task as not done. \n" +
                        "See you! \n"
        );

        while (true) {
            System.out.print("Enter command: ");
            String userInput = scanner.nextLine();

            if (userInput.isEmpty()) {
                System.out.println("Please enter a valid command.");
                continue;
            }

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTaskList(taskArray);
            } else if (userInput.toLowerCase().startsWith("mark")) {
                completeTask(userInput, taskArray);
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                unmarkTask(userInput, taskArray);
            } else {
                System.out.println("You entered: " + userInput);
                taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
                taskArray[taskArray.length - 1] = new Task(userInput);
            }
        }

        scanner.close();
    }
}




