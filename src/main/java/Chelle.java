import java.util.Scanner;

public class Chelle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] messages = new String[100];
        int messageCount = 0;

        System.out.println("Hello! I'm Chelle.\nI like to talkity talkity talk!");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Chelle: Bye! Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Chelle: ");
                displayMessages(messages, messageCount);
            } else {
                if (messageCount < messages.length) {
                    messages[messageCount] = userInput;
                    messageCount++;
                    System.out.println("Chelle: added: " + userInput);
                } else {
                    System.out.println("Chelle: Message limit reached. Cannot add more messages.");
                }
            }
        }

        scanner.close();
    }

    private static void displayMessages(String[] messages, int count) {
        if (count == 0) {
            System.out.println("No messages to display.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + messages[i]);
            }
        }
    }
}
