import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Display a greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String userInput;
        String[] tasks = new String[100]; // Array to store tasks
        int taskCount = 0;

        // Continue reading user input until "bye" is entered
        while (true) {
            userInput = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks added yet.");

                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }

            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("added: " + userInput);
            }
            System.out.println("____________________________________________________________");
        }

        // Close the Scanner
        scanner.close();
    }
}
