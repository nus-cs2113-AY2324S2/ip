import java.util.Scanner;

public class Quokka {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void addTask(Task newTask) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = newTask;
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("    Sorry, the task list is full. You cannot add more tasks.");
        }
    }

    private static Todo parseTodoTask(String userInput) {
        String description = userInput.substring("todo".length()).trim();
        return new Todo(description);
    }

    private static Deadline parseDeadlineTask(String userInput) {
        String[] parts = userInput.split("/by", 2);
        if (parts.length == 2) {
            String description = parts[0].substring("deadline".length()).trim();
            String by = parts[1].trim();
            return new Deadline(description, by);
        } else {
            System.out.println("    Invalid deadline format. Please use: deadline [description] /by [date/time]");
            return null;
        }
    }

    private static Event parseEventTask(String userInput) {
        String[] parts = userInput.split("/from", 2);
        if (parts.length == 2) {
            String description = parts[0].substring("event".length()).trim();
            String[] dateTimes = parts[1].split("/to", 2);
            if (dateTimes.length == 2) {
                String from = dateTimes[0].trim();
                String to = dateTimes[1].trim();
                return new Event(description, from, to);
            } else {
                System.out.println("    Invalid event format. Please use: event [description] /from [start] /to [end]");
                return null;
            }
        } else {
            System.out.println("    Invalid event format. Please use: event [description] /from [start] /to [end]");
            return null;
        }
    }

    private static void displayTasks() {
        if (taskCount == 0) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + ". " + tasks[i]);
            }
        }
    }

    private static void markTaskAsDone(String userInput) {
        updateTaskStatus(userInput, true, "Nice! I've marked this task as done:");
    }

    private static void markTaskAsNotDone(String userInput) {
        updateTaskStatus(userInput, false, "OK, I've marked this task as not done yet:");
    }

    private static void updateTaskStatus(String userInput, boolean newStatus, String statusMessage) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 2) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].setStatus(newStatus);
                System.out.println("    " + statusMessage);
                System.out.println("      " + tasks[taskIndex]);
            } else {
                System.out.println("    Invalid task index.");
            }
        } else {
            System.out.println("    Please provide a valid task index to mark as done or not done.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Quokka");
        System.out.println("What can I do for you?");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            }

            // Check if the user wants to list tasks
            if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                // Handle different types of tasks
                if (userInput.toLowerCase().startsWith("mark ")) {
                    markTaskAsDone(userInput);
                } else if (userInput.toLowerCase().startsWith("unmark ")) {
                    markTaskAsNotDone(userInput);
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    addTask(parseTodoTask(userInput));
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    addTask(parseDeadlineTask(userInput));
                } else if (userInput.toLowerCase().startsWith("event")) {
                    addTask(parseEventTask(userInput));
                } else {
                    System.out.println("    I'm sorry, I don't understand that command.");
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}
