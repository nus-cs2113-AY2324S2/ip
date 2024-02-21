import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GOne {
    private List<Task> tasks;

    public GOne() {
        this.tasks = new ArrayList<>();
    }

    public void start() {
        System.out.println("Hello! I'm G.one");
        System.out.println("--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.print("Whats up? ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTaskList();
            } else {
                processUserInput(userInput);
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private void processUserInput(String userInput) {
        String[] parts = userInput.split(" ", 2); // Split command and argument
        String command = parts[0].trim().toLowerCase();
        String argument = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
            case "todo":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    addTodoTask(argument);
                }
                break;
            case "deadline":
                if (argument.isEmpty() || !argument.contains("/by")) {
                    System.out.println("OOPS!!! Invalid deadline format. Use: deadline <description> /by <deadline>");
                } else {
                    addDeadlineTask(argument);
                }
                break;
            case "event":
                if (argument.isEmpty() || !argument.contains("/from") || !argument.contains("/to")) {
                    System.out.println("OOPS!!! Invalid event format. Use: event <description> /from <start time> /to <end time>");
                } else {
                    addEventTask(argument);
                }
                break;
            case "mark":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! Please provide the task number to mark.");
                } else {
                    markTask(argument);
                }
                break;
            case "unmark":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! Please provide the task number to unmark.");
                } else {
                    unmarkTask(argument);
                }
                break;
            case "list":
                displayTaskList();
                break;
            default:
                System.out.println("That is not a valid command.");
        }
    }

    private void markTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void addTodoTask(String description) {
        Task task = new TodoTask(description);
        tasks.add(task);
        System.out.println("Alright buddy, Added that:");
        System.out.println("  " + task);
        printTaskCount();
    }

    private void addDeadlineTask(String descriptionAndBy) {
        String[] parts = descriptionAndBy.split(" /by ");
        if (parts.length != 2) {
            System.out.println("Invalid deadline format.");
            return;
        }
        Task task = new DeadlineTask(parts[0], parts[1]);
        tasks.add(task);
        System.out.println("Alright buddy, Added this:");
        System.out.println("  " + task);
        printTaskCount();
    }

    private void addEventTask(String descriptionAndTime) {
        String[] parts = descriptionAndTime.split(" /from ");
        if (parts.length != 2) {
            System.out.println("Invalid event format.");
            return;
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            System.out.println("Invalid event format.");
            return;
        }
        Task task = new EventTask(parts[0], timeParts[0], timeParts[1]);
        tasks.add(task);
        System.out.println("Alright buddy, Added this:");
        System.out.println("  " + task);
        printTaskCount();
    }

    private void displayTaskList() {
        System.out.println("Your Task List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }

    private void printTaskCount() {
        System.out.println("Ok busy guy, Now you have " + tasks.size() + " task(s) in the list");
    }

    public static void main(String[] args) {
        GOne gOne = new GOne();
        gOne.start();
    }
}
