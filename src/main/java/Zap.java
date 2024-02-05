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

    /**
     * main interaction loop of ZAP
     * continuously prompts the user to enter commands and responds accordingly.
     * The loop breaks when the user enters "thank you and bye."
     * handles various commands like listing tasks,
     * marking tasks as done, unmarking and adding new tasks
     */
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
            } else if (userCommand.startsWith("todo")) {
                addTodoTask(userCommand);
            } else if (userCommand.startsWith("deadline")) {
                addDeadline(userCommand);
            } else if (userCommand.startsWith("event")) {
                addEvent(userCommand);
            } else {
                System.out.println(userCommand);
                addTask(userCommand);
            }
        } while (true);
        scanner.close();
    }

    /**
     * `addTask` method adds new task to the list of tasks
     * creates a new `Task` object with description,
     * adds it to the `tasks` list
     */
    private static void addTask(String taskDescription) {
        if (taskDescription.isEmpty()) {
            System.out.println("Woi. You think I robot then can waste my time >:(");
        } else {
            tasks.add(new Task(taskDescription));
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + taskDescription);
            System.out.println("____________________________________________________________");
        }
    }

    private static void addTodoTask(String userCommand) {

        String taskDescription = userCommand.substring(4).trim();
        tasks.add(new TodoTask(taskDescription));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. Zappy boy added this task:");
        System.out.println("   [T][ ]  " + taskDescription);
        if (tasks.size() == 1) {
            System.out.println(" Now you have 1 task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }
        System.out.println("____________________________________________________________");
    }

    private static void addDeadline(String userCommand) {
        String[] descParts = userCommand.split("deadline");
        String[] deadlineParts = descParts[1].split("/by", 2);

        if (deadlineParts.length != 2) {
            System.out.println("Invalid deadline format. Please use '/by' to specify day.");
            return;
        }

        String description = deadlineParts[0];
        String deadline = deadlineParts[1].trim();

        tasks.add(new DeadlineTask(description, deadline));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. Zappy boy added this task:");
        System.out.println("   [D][ ] " + description + " (By: " + deadline + ")");
        if (tasks.size() == 1) {
            System.out.println(" Now you have 1 task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }
        System.out.println("____________________________________________________________");
    }

    private static void addEvent(String userCommand) {

        String[] descParts = userCommand.split("event");
        String[] eventParts = descParts[1].split("/from", 2);

        if (eventParts.length != 2) {
            System.out.println("Invalid event format. Please use '/from' to specify start and end time.");
            return;
        }

        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("/to", 2);

        if (timeParts.length != 2) {
            System.out.println("Invalid event format. Please use '/to' to specify end time.");
            return;
        }

        String startTime = timeParts[0].trim();
        String endTime = timeParts[1].trim();
        tasks.add(new EventTask(description, startTime, endTime));

        System.out.println("____________________________________________________________");
        System.out.println(" Got it. Zappy boy added this task:");
        System.out.println("   [E][ ] " + description + " (From: " + startTime + " To: " + endTime + ")");
        if (tasks.size() == 1) {
            System.out.println(" Now you have 1 task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * 'displayTasks' method displays all the lists -- marked and unmarked
     * when the command 'list' is inputted by the user
     */
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

    /**
     * `markTask` method marks a task as done.
     * it extracts the task index, and updates the task's status
     */
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

    /**
     * `unmarkTask` method unmarks a task that was originally marked as done.
     * it extracts the task index, and updates the task's status, then prints a confirmation message.
     */
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