import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Sunny {
    private static ArrayList<Task> tasksList = new ArrayList<>();
    private static int counter;
    private static final String FILE_PATH = "./data/sunny.txt";

    public static void main(String[] args) {
        loadTasksFromFile(); // Load tasks from the file upon startup
        
        System.out.println("Hello! I'm Sunny");
        System.out.println("What can I do for you?");
        System.out.println(" ");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    printTaskList();
                } else if (command.startsWith("mark")) {
                    markTaskAsDone(command);
                } else if (command.startsWith("unmark")) {
                    unmarkTaskAsDone(command);
                } else if (command.startsWith("todo")) {
                    handleTodoCommand(command);
                } else if (command.startsWith("deadline")) {
                    handleDeadlineCommand(command);
                } else if (command.startsWith("event")) {
                    handleEventCommand(command);
                } else {
                    handleUnknownCommand(command);
                }
                // For testing purposes, you can add some tasks
                tasksList.add(new Todo("Test Todo"));
                tasksList.add(new Deadline("Test Deadline", "Mon"));
                tasksList.add(new Event("Test Event", "1pm", "2pm"));

                saveTasksToFile(); // Save tasks to the file whenever the task list changes
            } catch (Exception e) {
                handleErrors(e);
            }
        }
    }

    private static void loadTasksFromFile() {
        try {
            Path path = Paths.get(FILE_PATH);

            if (!Files.exists(path)) {
                // Handle the case where the file doesn't exist yet
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                System.out.println("File created: " + FILE_PATH);
            }


            Scanner scanner = new Scanner(new File(FILE_PATH));
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                System.out.println("Read from file: " + fileLine);

                // Parse and add the task directly
                parseAndAddTask(fileLine);
            }
            // Print the loaded tasks
            printTaskList();

            System.out.println("Tasks loaded successfully!");
        } catch (IOException e) {
            handleErrors(e);
        }
    }

    private static void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (Task task : tasksList) {
                writer.println(task.toFileString());
            }
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            handleErrors(e);
        }
    }

    private static void parseAndAddTask(String data) {
        String[] parts = data.split(" \\| ");

        if (parts.length < 3) {
            // Not enough data to create a valid task
            return;
        }

        String type = parts[0].toLowerCase(); // Convert to lowercase for case-insensitivity
        String status = parts[1];
        String description = parts[2];

        switch (type) {
        case "t":
            tasksList.add(Todo.fromFileFormat(description));
            break;
        case "d":
            tasksList.add(Deadline.fromFileFormat(description));
            break;
        case "e":
            tasksList.add(Event.fromFileFormat(description));
            break;
        default:
            // Unknown task type
            break;
        }

    }

    private static void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println(i + 1 + ". " + tasksList.get(i));
        }
        System.out.println(" ");
    }


    private static void markTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasksList.get(taskIndex - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksList.get(taskIndex - 1));
        } else {
            printInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    private static void unmarkTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasksList.get(taskIndex - 1).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasksList.get(taskIndex - 1));
        } else {
            printInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= counter;
    }

    private static void handleTodoCommand(String command) {
        try {
            if (command.length() <= 5) {
                throw new StringIndexOutOfBoundsException();
            }

            tasksList.add(new Todo(command.substring(5)));
            System.out.println(tasksList.get(counter));
            counter++;
            printTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            handleErrors(e);
        }
    }

    private static void handleDeadlineCommand(String command) {
        try {
            int dividerPosition = command.indexOf("/by ");
            if (dividerPosition == -1) {
                throw new StringIndexOutOfBoundsException();
            }

            tasksList.add(new Deadline(command.substring(9, dividerPosition - 1), command.substring(dividerPosition + 4)));
            System.out.println(tasksList.get(counter));
            counter++;
            printTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            handleErrors(e);
        }
    }

    private static void handleEventCommand(String command) {
        try {
            int from = command.indexOf("/from ");
            int to = command.indexOf("/to ");
            if (from == -1 || to == -1) {
                throw new StringIndexOutOfBoundsException();
            }

            tasksList.add(new Event(command.substring(6, from - 1), command.substring(from + 6, to - 1), command.substring(to + 4)));
            System.out.println(tasksList.get(counter));
            counter++;
            printTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            handleErrors(e);
        }
    }

    private static void handleUnknownCommand(String command) {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(" ");
    }



    private static void printInvalidTaskIndexMessage() {
        System.out.println("Invalid task index. Please provide a valid task index.");
    }

    private static void printTaskCountMessage() {
        String taskNoun = (counter == 1) ? "task" : "tasks";
        System.out.println("Now you have " + counter + " " + taskNoun + " in the list." + System.lineSeparator());
    }

    private static int extractTaskIndex(String command) {
        String[] parts = command.split(" ");
        return (parts.length >= 2) ? Integer.parseInt(parts[1]) : -1;
    }

    private static void handleErrors(Exception e) {
        if (e instanceof IOException) {
            System.out.println("OOPS!!! An error occurred while handling the file.");
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The command seems to be incomplete or incorrect.");
        } else if (e instanceof NumberFormatException) {
            System.out.println("OOPS!!! Please provide a valid task index.");
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } else {
            System.out.println("OOPS!!! I'm sorry, but I encountered an unexpected error.");

        }
        e.printStackTrace(); // Print stack trace for debugging purposes
        System.out.println(" ");
    }

}