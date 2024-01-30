import java.util.Scanner;
import java.util.ArrayList;

public class Gene {
    private static final String botName = "Gene";
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<String> toDoList = new ArrayList<>();

    private void printLineSeparation() {
        for (int i = 0; i < 29; i++) {
            System.out.print("__");
        }
        System.out.println("__");
    }

    public void startChat() {
        printLineSeparation();
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        printLineSeparation();

        // Read user input in a loop
        while (true) {
            System.out.print("User Input: ");
            String userInput = scanner.nextLine();

            // End chat if user types "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            // Print out all list items if user types "list"
            if (userInput.equalsIgnoreCase("list")) {
                printListItems();
                continue;
            }

            // Add items into list
            addItem(userInput);
        }
    }

    private void addItem(String command) {
        toDoList.add(command);
        printLineSeparation();
        System.out.println("added: " + command);
        printLineSeparation();
    }

    private void printListItems() {
        printLineSeparation();
        System.out.println("Here are the items in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println((i + 1) + ". " + toDoList.get(i));
        }
        printLineSeparation();
    }

    public void endChat() {
        printLineSeparation();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparation();
    }
}
