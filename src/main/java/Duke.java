import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "   _____                     _   \n" +
                        "  / ____|                   | |  \n" +
                        " | |     __ _ _ __ _ __ ___ | |_ \n" +
                        " | |    / _` | '__| '__/ _ \\| __|\n" +
                        " | |___| (_| | |  | | | (_) | |_ \n" +
                        "  \\_____\\__,_|_|  |_|  \\___/ \\__|\n";
        System.out.println("Hello from\n" + logo);

        // Chatbot functionality
        greetUser();
        handleUserInput();
        sayGoodbye();
    }

    private static final String messageDivider = "-------------------------------------";

    private static void greetUser() {
        System.out.println(messageDivider);
        System.out.println("Hello! I'm Carrot!");
        System.out.println("What can I do for you?");
        System.out.println(messageDivider);
    }

    private static void handleUserInput() {
        // Scanner object for userInput
        Scanner in = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = in.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                break;
            }
            echoUserCommand(userInput);
        }
    }

    private static void echoUserCommand(String userInput) {
        System.out.println(messageDivider);
        System.out.println(userInput);
        System.out.println(messageDivider);
    }

    private static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(messageDivider);
    }
}
