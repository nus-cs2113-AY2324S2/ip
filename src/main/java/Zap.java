import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Zap {
    private static final List<String> tasks = new ArrayList<>();
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

    // User Greeting
    private static void greeting() {
        String chatbotName = "ZAP";
        System.out.println("____________________________________________________________");
        System.out.println(" 你好! Hello! Konichiwa! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    // Process User Commands
    private static void processCommands() {
        Scanner scanner = new Scanner(System.in);
        String userCommand;

        do {
            System.out.print("Enter a command: ");
            userCommand = scanner.nextLine();

            if (userCommand.equalsIgnoreCase("thank you and bye")) {
                scanner.close();
                break;
            } else if (userCommand.equalsIgnoreCase("hi")) {
                System.out.println("Hello! I am ZAP and I am at your service!");
            } else if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("You should say thank you, then say bye.");
            } else if (userCommand.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                System.out.println(userCommand);
                addTask(userCommand);
            }
        } while (true);
        scanner.close();
    }

    //Add tasks
    private static void addTask(String task) {
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Zap has Added: " + task);
        System.out.println("____________________________________________________________");
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println(" There are no inputted tasks.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }
    }

    // Exit Chatbot
    private static void exit() {
        System.out.println("Bye! See you again next time :)");
        System.out.println("____________________________________________________________");
    }
}
