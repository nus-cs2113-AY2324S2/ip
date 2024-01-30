import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Brad";
        System.out.println("Hello I am " + name + ".\n");
        System.out.println("How can I help you today?\n");
        Scanner userInput = new Scanner(System.in);
        String[] messages = new String[100];
        int messageCount = 0;
        while (true) {
            String x = userInput.nextLine();
            boolean exit = x.equals("bye");
            boolean list = x.equals("list");
            if (exit) {
                break;
            } else if (list) {
                listMessages(messages);
            } else {
                addList(x, messageCount, messages);
                messageCount++;
            }
        }
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }
    private static void printSeparator()
    {
        String separator = "____________________________________________________________";
        System.out.println(separator);
    }

    private static void echo(String message)
    {
        printSeparator();
        System.out.println(message);
        printSeparator();
    }
    private static void addList(String message, int messageCount, String[] messages) {
        printSeparator();
        System.out.println("added: " + message + "\n");
        printSeparator();
        messages[messageCount] = message;
    }


    private static void listMessages(String[] message) {
        printSeparator();
        for (int i = 0; i < 100; i++) {
            if (message[i] == null) {
                break;
            }
            System.out.println(i+1 + ". " + message[i]);
        }
        printSeparator();
    }


}