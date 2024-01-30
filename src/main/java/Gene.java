import java.util.Scanner;

public class Gene {
    private static final String botName = "Gene";
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

            processCommand(userInput);
        }
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
                if (parts.length > 1 && isNumeric(parts[1])) {
                    taskList.markTaskAsDone(Integer.parseInt(parts[1]));
                } else {
                    System.out.println("Please provide a valid task number to mark as done.");
                }
                break;
            case "unmark":
                if (parts.length > 1 && isNumeric(parts[1])) {
                    taskList.markTaskAsNotDone(Integer.parseInt(parts[1]));
                } else {
                    System.out.println("Please provide a valid task number to mark as not done.");
                }
                break;
            default:
                taskList.addTask(command);
        }
    }

    public void endChat() {
        printLineSeparation();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparation();
    }
}
