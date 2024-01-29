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

        // List items
        String[] listOfTasks = new String[100];
        int taskCount = 0;

        while (true) {
            userInput = in.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equals("")) {
                continue;
            } else if (userInput.equalsIgnoreCase("list")) {
                echoListItems(listOfTasks, taskCount);
            } else {
                addTaskToList(userInput, listOfTasks, taskCount);
                taskCount++;
            }
        }
    }

    private static void addTaskToList(String userInput, String[] listOfTasks, int index) {
        listOfTasks[index] = userInput;

        System.out.println(messageDivider);
        System.out.println("added: " + userInput);
        System.out.println(messageDivider);
    }

    private static void echoListItems(String[] listOfTasks, int taskCount) {
        System.out.println(messageDivider);
        if (taskCount == 0) {
            System.out.println("No tasks added");
        } else {
            for (int i = 0; listOfTasks[i] != null; i++) {
                System.out.printf("%d. %s%n", i+1, listOfTasks[i]);
            }
        }
        System.out.println(messageDivider);
    }

    private static void sayGoodbye() {
        System.out.println(messageDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(messageDivider);
    }
}
