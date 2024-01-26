import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> commands = new ArrayList<>();

    public static void main(String[] args) {
        String name = "Jonas";
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayHistory();
            } else {
                commands.add(userInput);
                System.out.println("____________________________________________________________");
                System.out.println("Added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }

    private static void displayHistory() {
        System.out.println("____________________________________________________________");
        System.out.println("Command History:");
        for (String command : commands) {
            System.out.println(command);
        }
        System.out.println("____________________________________________________________");
    }
}
