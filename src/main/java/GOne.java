import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class GOne {
    private static final String FILE_PATH = "./data/tasks.txt";
    private List<Task> tasks;

    public GOne() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile(); // Load tasks from file when the object is created

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
            case "delete":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! Please provide the task number to delete.");
                } else {
                    deleteTask(argument);
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
            saveTasksToFile(); // Call saveTasksToFile after marking a task as done
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
            saveTasksToFile(); // Call saveTasksToFile after marking a task as undone
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
        saveTasksToFile(); // Call saveTasksToFile after adding a new task
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
        saveTasksToFile(); // Call saveTasksToFile after adding a new task
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
        saveTasksToFile(); // Call saveTasksToFile after adding a new task
    }

    private void deleteTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            printTaskCount();
            saveTasksToFile(); // Call saveTasksToFile after deleting a task
        } else {
            System.out.println("Invalid task number.");
        }
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
    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                switch (taskType) {
                    case "T":
                        tasks.add(new TodoTask(description));
                        break;
                    case "D":
                        tasks.add(new DeadlineTask(description, parts[3].trim()));
                        break;
                    case "E":
                        tasks.add(new EventTask(description, parts[3].trim(), parts[4].trim()));
                        break;
                    default:
                        System.out.println("Invalid task type found in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private void saveTasksToFile() {
        try {
            // Create directory if it doesn't exist
            Path directoryPath = Paths.get("./data");
            Files.createDirectories(directoryPath);

            // Write tasks to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Task task : tasks) {
                    String line;
                    if (task instanceof TodoTask) {
                        line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                    } else if (task instanceof DeadlineTask) {
                        DeadlineTask deadlineTask = (DeadlineTask) task;
                        line = "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getBy();
                    } else if (task instanceof EventTask) {
                        EventTask eventTask = (EventTask) task;
                        line = "E | " + (task.isDone() ? "1" : "0") + " | " + eventTask.getDescription() + " | " + eventTask.getFrom() + " | " + eventTask.getTo();
                    } else {
                        continue; // Skip unknown task types
                    }
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        GOne gOne = new GOne();
        gOne.start();
    }
}
