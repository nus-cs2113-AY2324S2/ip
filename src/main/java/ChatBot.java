import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatBot {
    private String chatbotName;
    private List<Task> tasks;



    public ChatBot(String chatbotName) {
        this.chatbotName = chatbotName;
        this.tasks = new ArrayList<>();
    }

    public void startChat() {

        String horizontalLines = "____________________________________________________________";
        System.out.println(horizontalLines);
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLines);

        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                printFormattedMessage("Bye. Hope to see you again soon!");
                break;
            } else if (command.startsWith("add ")) {
                addTask(command.substring(4));
            } else if (command.equalsIgnoreCase("list")) {
                printTaskList();
            } else if (command.startsWith("mark ")) {
                markTaskAsDone(command.substring(5));
            } else if (command.startsWith("unmark ")) {
                unmarkTaskAsDone(command.substring(7));
            } else {
                printFormattedMessage("Unknown command. Please enter 'add', 'list', 'mark', 'unmark', or 'bye'.");
            }
        }
    }

    private void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        printFormattedMessage("added: " + taskDescription);
    }

    private void printTaskList() {

        String horizontalLines = "____________________________________________________________";

        int tasks_size = tasks.size();

        if(tasks_size == 0){

            printFormattedMessage("No tasks added. Add now!");
        } else{

            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println( " " + (i + 1) + ". " + task.getStatusIcon() + " " + task.getDescription());
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

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot("EDITH");
        chatBot.startChat();
    }
}
