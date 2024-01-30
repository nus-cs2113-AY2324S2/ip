import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Zap {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = """
              _____   _   _   ____
             |__  /  / \\ / | |  _ \\
               / /  / _ \\| | | |_) |
              / /_ / ___ \\ | |  __/
             /____/_/   \\_\\_| |_|
            """;

        System.out.println("Hello from\n" + logo);
        greeting();
        processCommands();
        exit();
    }

    private static void greeting() {
        String chatbotName = "ZAP";
        System.out.println("____________________________________________________________");
        System.out.println(" 你好! Hello! Konichiwa! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void processCommands() {
        Scanner scanner = new Scanner(System.in);
        String userCommand;

        do {
            System.out.print("Enter a command: ");
            userCommand = scanner.nextLine();

            if (userCommand.equalsIgnoreCase("thank you and bye")) {
                break;
            } else if (userCommand.equalsIgnoreCase("hi")) {
                System.out.println("Hello! I am ZAP and I am at your service!");
            } else if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("You should say thank you, then say bye.");
            } else if (userCommand.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (userCommand.startsWith("mark")) {
                markTask(userCommand);
            } else if (userCommand.startsWith("unmark")) {
                unmarkTask(userCommand);
            } else {
                System.out.println(userCommand);
                addTask(userCommand);
            }
        } while (true);

        scanner.close();
    }

    //add tasks
    private static void addTask(String taskDescription) {
        tasks.add(new Task(taskDescription));
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + taskDescription);
        System.out.println("____________________________________________________________");
    }

    //display all tasks
    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println(" Tasks list is empty.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }
    }

    //mask tasks as done
    private static void markTask(String userCommand) {
        String[] characters = userCommand.split("\\s+");

        if (characters.length != 2 || !characters[1].matches("\\d+")) {
            System.out.println("Invalid command format. Please use 'mark <task index>'.");
            return;
        }

        int taskIndex = Integer.parseInt(characters[1]) - 1;

        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex));
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Invalid task index. Please enter a valid number.");
        }
    }

    //unmark tasks that were originally marked
    private static void unmarkTask(String userCommand) {
        String[] characters = userCommand.split("\\s+");

        if (characters.length == 2 && characters[1].matches("\\d+")) {
            int taskIndex = Integer.parseInt(characters[1]) - 1;

            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Invalid format. Please check your spelling or punctuation.");
        }
    }

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < tasks.size();
    }

    //saying bye to chatbot
    private static void exit() {
        System.out.println("Bye! See you again sooooooooon :)");
        System.out.println("____________________________________________________________");
    }
}

//A-classes
class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }
    public String toString() {
        if (done) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}