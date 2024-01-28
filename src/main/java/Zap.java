import java.util.Scanner;

public class Zap {
    public static void main(String[] args) {
        String logo = "  _____   _   _   ____\n"
                + " |__  /  / \\ / | |  _ \\\n"
                + "   / /  / _ \\| | | |_) |\n"
                + "  / /_ / ___ \\ | |  __/\n"
                + " /____/_/   \\_\\_| |_|";
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
                System.out.println("Hello! I am ZAP and I will just repeat everything you say!");
            } else if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("You should say thank you, then say bye.");
            } else {
                System.out.println(userCommand);
            }
        } while (true);
        scanner.close();
    }
    // Exit Chatbot
    private static void exit() {
        System.out.println("Bye! See you again next time :)");
        System.out.println("____________________________________________________________");
    }
}
