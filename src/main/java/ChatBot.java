import taskPackage.Deadlines;
import edithExceptionPackage.ChatBotExceptions;
import taskPackage.Events;
import taskPackage.Task;
import taskPackage.ToDos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatBot {
    private String chatbotName;
    private List<Task> tasks;
    private Scanner in;
    private static final String FILENAME = "text.txt";
    private static final String DIRECTORY = "src/data";


    public ChatBot(String chatbotName) throws ChatBotExceptions {
        this.chatbotName = chatbotName;
        this.tasks = new ArrayList<>();
        this.in = new Scanner(System.in);
        loadTasksFromFile();
    }

    public void startChat() {
        printWelcomeMessage();

        while (true) {

            String command = in.nextLine().trim();

            try {
                switch (command.toLowerCase()) {
                case "bye":
                    printFormattedMessage("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    printTaskList();
                    break;
                case "mark":
                    markTaskAsDone(command.substring(5));
                    break;
                case "unmark":
                    unmarkTaskAsDone(command.substring(7));
                    break;
                case "todo":
                    addTask(command.substring(5), "todo");
                    break;
                case "event":
                    addTask(command.substring(6), "event");
                    break;
                case "deadline":
                    addTask(command.substring(9), "deadline");
                    break;
                case "delete":
                    deleteTask(command.substring(7));
                    break;
                default:
                    printFormattedMessage("Unknown command. Please enter 'todo', 'event', " +
                            "'deadline', 'list', 'mark', 'unmark', 'delete', or 'bye'.");
                    break;
                }
            } catch (ChatBotExceptions e) {
                printFormattedMessage(e.getMessage());
            }
        }
    }


    public void saveTasksToFile() {
        // Get the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Define the relative path to the file
        String relativeFilePath = Paths.get(currentDirectory, DIRECTORY, FILENAME).toString();

        Path filePath = Paths.get(relativeFilePath);

        try {
            // Create the parent directory if it doesn't exist
            Files.createDirectories(filePath.getParent());

            // Write tasks to the file
            // Use StandardOpenOption.TRUNCATE_EXISTING to clear the file before writing
            Files.write(filePath, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

            for (Task task : tasks) {
                Files.write(filePath, (task.toFileString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            }
            System.out.println("Tasks have been saved to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to " + filePath);
            e.printStackTrace();
        }
    }


    private void loadTasksFromFile() {
        String currentDirectory = System.getProperty("user.dir");
        String relativeFilePath = Paths.get(currentDirectory, DIRECTORY, FILENAME).toString();
        Path filePath = Paths.get(relativeFilePath);

        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    if (line.trim().isEmpty()) {
                        // Skip empty lines
                        continue;
                    }
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    String additionalInfo = "";

                    // Check if there are additional parts for Deadline and Event tasks
                    if (parts.length > 3) {
                        additionalInfo = parts[3];
                    }

                    Task task;
                    switch (type) {
                    case "T":
                        task = new ToDos(description, isDone);
                        break;
                    case "D":
                        task = new Deadlines(description, additionalInfo, isDone);
                        break;
                    case "E":
                        if (!additionalInfo.isEmpty()) {
                            String[] eventInfo = additionalInfo.split(" ");
                            String fromDate = eventInfo[0];
                            String toDate = eventInfo[1];
                            task = new Events(description, fromDate, toDate, isDone);
                        } else {
                            task = new Events(description, "", "", isDone);
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid task type: " + type);
                    }
                    tasks.add(task);
                }
                System.out.println("Tasks have been loaded from " + filePath);
            } else {
                System.out.println("No tasks file found at " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from " + filePath);
            e.printStackTrace();
        }
    }



    private void deleteTask(String taskNumber) throws ChatBotExceptions {

        if (tasks.isEmpty()) {
            throw new ChatBotExceptions("No tasks to delete. taskPackage.Task list is empty.");
        }

        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println("   " + removedTask.getStatusIcon() + " " + removedTask.getDescription());
                printFormattedMessage(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                printFormattedMessage("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            printFormattedMessage("Invalid command. Please enter a valid task number to delete.");
        }
    }

    private void addTask(String taskDescription, String taskType) throws ChatBotExceptions {
        Task newTask;

        if (taskType.equals("todo")) {
            newTask = new ToDos(taskDescription, false); // Initialize isDone as false
        } else if (taskType.equals("deadline")) {
            String[] parts = taskDescription.split(" by ");
            if (parts.length < 2) {
                throw new ChatBotExceptions("Invalid deadline format. " +
                        "Please use 'deadline <description> by <date>'.");
            }
            String description = parts[0];
            String byDate = parts[1];
            newTask = new Deadlines(description, byDate, false);
        } else if (taskType.equals("event")) {
            String[] parts = taskDescription.split(" from | to ");
            if (parts.length < 3) {
                throw new ChatBotExceptions("Invalid event format. " +
                        "Please use 'event <description> from <start> to <end>'.");
            }
            String description = parts[0];
            String fromDate = parts[1];
            String toDate = parts[2];
            newTask = new Events(description, fromDate, toDate, false); // Initialize isDone as false
        } else {
            throw new ChatBotExceptions("Invalid command.");
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        if (newTask instanceof Deadlines) {
            Deadlines deadline = (Deadlines) newTask;
            System.out.println("   " + newTask.getStatusIcon() + " " + deadline.getDescription());
        } else if (newTask instanceof Events) {
            Events event = (Events) newTask;
            System.out.println("   " + newTask.getStatusIcon() + " " + event.getDescription());
        } else {
            System.out.println("   " + newTask.getStatusIcon() + " " + newTask.getDescription());
        }
        printFormattedMessage(" Now you have " + tasks.size() + " tasks in the list.");
    }


    private void printTaskList() {

        String horizontalLines = "____________________________________________________________";

        int tasks_size = tasks.size();

        if (tasks_size == 0) {

            printFormattedMessage("No tasks added. Add now!");
        } else {

            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(" " + (i + 1) + ". "
                        + task.getStatusIcon() + " " + task.getDescription());
            }

            System.out.println(horizontalLines);

        }
    }
    private void printFormattedMessage(String message) {
        String horizontalLines = "____________________________________________________________";
        System.out.println("  " + message);
        System.out.println(horizontalLines);
    }

    private void markTaskAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                printFormattedMessage("   " + task.getStatusIcon() + " " + task.getDescription());
            } else {
                printFormattedMessage("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            printFormattedMessage("Invalid command. Please enter a valid task number to mark as done.");
        }
    }

    private void unmarkTaskAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                printFormattedMessage("   " + task.getStatusIcon() + " " + task.getDescription());
            } else {
                printFormattedMessage("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            printFormattedMessage("Invalid command. Please enter a valid task number to unmark.");
        }
    }

    private void printWelcomeMessage() {
        String horizontalLines = "____________________________________________________________";
        System.out.println(horizontalLines);
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLines);
    }

    public static void main(String[] args) throws ChatBotExceptions {

        ChatBot chatBot = new ChatBot("EDITH");
        chatBot.startChat();
        chatBot.saveTasksToFile();
    }
}