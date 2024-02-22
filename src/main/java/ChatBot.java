import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatBot {
    private String chatbotName;
    private List<Task> tasks;
    private Scanner in;

    public ChatBot(String chatbotName) {
        this.chatbotName = chatbotName;
        this.tasks = new ArrayList<>();
        this.in = new Scanner(System.in);
    }

    public void startChat() {
        printWelcomeMessage();

        while (true) {

            String command = in.nextLine().trim();

            try {
                if (command.equalsIgnoreCase("bye")) {
                    printFormattedMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    printTaskList();
                } else if (command.startsWith("mark ")) {
                    markTaskAsDone(command.substring(5));
                } else if (command.startsWith("unmark ")) {
                    unmarkTaskAsDone(command.substring(7));
                } else if (command.startsWith("todo ")) {
                    addTask(command.substring(5), "todo");
                } else if (command.startsWith("event ")) {
                    addTask(command.substring(6), "event");
                } else if (command.startsWith("deadline ")) {
                    addTask(command.substring(9), "deadline");
                } else if (command.startsWith("delete ")) {
                    deleteTask(command.substring(7));
                }else {
                    printFormattedMessage("Unknown command. Please enter 'todo', 'event', " +
                            "'deadline', 'list', 'mark', 'unmark', or 'bye'.");
                }
            } catch (ChatBotExceptions e) {
                printFormattedMessage(e.getMessage());
            }
        }
    }

    private void deleteTask(String taskNumber) throws ChatBotExceptions{

        if (tasks.isEmpty()) {
            throw new ChatBotExceptions("No tasks to delete. Task list is empty.");
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
            newTask = new ToDos(taskDescription);
        } else if (taskType.equals("deadline")) {
            String[] parts = taskDescription.split(" by ");
            if (parts.length < 2) {
                throw new ChatBotExceptions("Invalid deadline format. " +
                        "Please use 'deadline <description> by <date>'.");
            }
            String description = parts[0];
            String byDate = parts[1];
            newTask = new Deadlines(description, byDate);
        } else if (taskType.equals("event")) {
            String[] parts = taskDescription.split(" from | to ");
            if (parts.length < 3) {
                throw new ChatBotExceptions("Invalid event format. " +
                        "Please use 'event <description> from <start> to <end>'.");
            }
            String description = parts[0];
            String fromDate = parts[1];
            String toDate = parts[2];
            newTask = new Events(description, fromDate, toDate);
        } else {
            throw new ChatBotExceptions("Invalid command.");
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + newTask.getStatusIcon() + " " + newTask.getDescription());
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

    public static void main(String[] args) {

        ChatBot chatBot = new ChatBot("EDITH");
        chatBot.startChat();
    }
}