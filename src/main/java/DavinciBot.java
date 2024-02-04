import java.util.Arrays;
import java.util.Scanner;

/**
 * DavinciBot is a simple bot that allows the user to manage a to-do list.
 * The bot echoes commands entered by the user, adds tasks to the list,
 * marks tasks as done, and unmarks tasks.
 */
public class DavinciBot {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    /**
     * Selects the icon corresponding to the type of task inputted by the user.
     *
     * @param taskArray Array of tasks.
     */
    private static void iconSelector(Task[] taskArray) {
        for (int i = 0; i < taskArray.length; i++) {
            String taskType = (taskArray[i] instanceof Deadline) ? "[D]" :
                    (taskArray[i] instanceof Todo) ? "[T]" : "[E]";

            System.out.println((i + 1) + ". " + taskType +
                    " [" + taskArray[i].getStatusIcon() + "] " +
                    taskArray[i].getDescription());
        }
    }
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
            iconSelector(taskArray);
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
                System.out.println(LINE_SEPARATOR);
                System.out.println("Nice job! I've marked this task as done :D");
                System.out.println("[" + taskArray[taskIndex].getStatusIcon() + "] " +
                        taskArray[taskIndex].getDescription());
                System.out.println(LINE_SEPARATOR);
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
                System.out.println(LINE_SEPARATOR);
                System.out.println("OK, I've marked this task as not done, but stop being lazy!");
                System.out.println("[" + taskArray[taskIndex].getStatusIcon() + "] " +
                        taskArray[taskIndex].getDescription());
                System.out.println(LINE_SEPARATOR);
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Please specify the task index to unmark.");
        }
    }

    /**
     * Adds a task to the list in a sequential order, and handles the 3 types of commands,
     * todo, deadline, and event.
     *
     * @param userInput User input specifying the task.
     * @param taskArray Array of tasks.
     */
    private static Task[] getTasks(String userInput, Task[] taskArray) {
        Scanner taskScanner = new Scanner(userInput);
        String taskType = taskScanner.next().toLowerCase();
        String description = taskScanner.nextLine().trim();
        switch (taskType) {
        case "todo":
            taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
            taskArray[taskArray.length - 1] = new Todo(description);
            echoTask(taskArray);
            break;
        case "deadline":
            String[] deadlineParts = description.split("/by", 2);
            if (deadlineParts.length == 2) {
                taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
                taskArray[taskArray.length - 1] = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                echoTask(taskArray);
            } else {
                System.out.println("Invalid deadline format. Please use: deadline <description> /by <deadline>");
            }
            break;
        case "event":
            String[] eventParts = description.split("/from", 2);
            if (eventParts.length == 2) {
                String[] eventTimeParts = eventParts[1].split("/to", 2);
                if (eventTimeParts.length == 2) {
                    taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
                    taskArray[taskArray.length - 1] = new Event(eventParts[0].trim(), eventTimeParts[0].trim(), eventTimeParts[1].trim());
                    echoTask(taskArray);
                } else {
                    System.out.println("Invalid event format. Please use: event <description> /from <start> /to <end>");
                }
            } else {
                System.out.println("Invalid event format. Please use: event <description> /from <start> /to <end>");
            }
            break;
        default:
            System.out.println("Unknown task type. Please use 'todo', 'deadline', or 'event'.");
        }
        return taskArray;
    }

    /**
     * Prints and echos back the newly added task.
     *
     * @param taskArray Array of tasks.
     */
    private static void echoTask(Task[] taskArray) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray[taskArray.length - 1].toString());
        System.out.println("Now you have " + taskArray.length + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the starting message.
     */
    private static void printStartingMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm DavinciBot! I was the smartest man alive, but now I am just a list maker.");
        System.out.println("Enter commands, and I will echo them back to you, as well as add them to your list.");
        System.out.println("Type 'bye' to end the conversation.");
        System.out.println("Type 'list' to see your to-do list.");
        System.out.println("Type 'mark' to mark a task as done.");
        System.out.println("Type 'unmark' to mark a task as not done.");
        System.out.println("Type 'todo <work>' to add a task to the list.");
        System.out.println("Type 'deadline <description> /by <deadline>' to add a task with a deadline to the list.");
        System.out.println("Type 'event <description> /from <start> /to <end>' to add an event to the list.");
        System.out.println("See you!");
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] taskArray = new Task[0];

        printStartingMessage();

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
            } else if (userInput.toLowerCase().startsWith("todo") ||
                    userInput.toLowerCase().startsWith("deadline") ||
                    userInput.toLowerCase().startsWith("event")) {
                taskArray = getTasks(userInput, taskArray);
            } else {
                System.out.println("Unknown command. Please enter a valid command.");
            }
        }
        scanner.close();
    }
}
