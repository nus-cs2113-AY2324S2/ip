import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatBot {
    private String chatbotName;
    private List<String> tasks;


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
            String command = in.nextLine();

            if (command.equalsIgnoreCase("bye")) {
                printFormattedMessage("Bye. Hope to see you again soon!");
                break;
            } else if (command.startsWith("add ")) {
                addTask(command.substring(4));
            } else if (command.equalsIgnoreCase("list")){
                printTaskList();
            }else {
                printFormattedMessage("Unknown command. Please enter 'add', 'list' or 'bye'.");
            }
        }
    }

    private void addTask(String task) {
        tasks.add(task);
        printFormattedMessage("added: " + task);
    }

    private void printTaskList() {
        String horizontalLines = "____________________________________________________________";
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(horizontalLines);
    }

    private void printFormattedMessage(String message) {
        String horizontalLines = "____________________________________________________________";
        System.out.println("  " + message);
        System.out.println(horizontalLines);
    }

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot("EDITH");
        chatBot.startChat();
    }
}
