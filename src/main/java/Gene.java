import java.util.Scanner;

public class Gene {
    private static final String BOT_NAME = "Gene";
    private final Scanner scanner = new Scanner(System.in);
    private TaskList taskList = new TaskList();

    public static void printLineSeparation() {
        for (int i = 0; i < 29; i++) {
            System.out.print("__");
        }
        System.out.println("__");
    }

    public void startChat() {
        printLineSeparation();
        System.out.println("Hello! I'm " + BOT_NAME);
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

            processCommand(userInput);
        }
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0].toLowerCase();

        switch (action) {
            case "list":
                taskList.printListItems();
                break;

            case "mark":
                MarkCommand.execute(command, taskList, true);
                break;

            case "unmark":
                MarkCommand.execute(command, taskList, false);
                break;

            case "todo":
                TodoCommand.execute(command, taskList);
                break;

            case "deadline":
                DeadlineCommand.execute(command, taskList);
                break;

            case "event":
                EventCommand.execute(command, taskList);
                break;

            default:
                printLineSeparation();
                System.out.println("Please add a valid command:");
                System.out.println("- list");
                System.out.println("- todo");
                System.out.println("- deadline");
                System.out.println("- event");
                printLineSeparation();
                break;
        }
    }

    public void endChat() {
        printLineSeparation();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparation();
    }
}
