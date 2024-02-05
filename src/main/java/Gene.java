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

            case "todo":
                String toDoParts = command.replaceFirst("\\S+", "");
                Todo newToDo = new Todo(toDoParts.trim());
                taskList.addTask(newToDo);
                break;

            case "deadline":
                String[] deadlineParts = command.replaceFirst("\\S+", "").split("/");
                Deadline newDeadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1]
                        .replace("by", "").trim());
                taskList.addTask(newDeadline);
                break;

            case "event":
                String[] eventParts = command.replaceFirst("\\S+", "").split("/");
                Event newEvent = new Event(eventParts[0].trim(), eventParts[1].replace("from", "").trim()
                        , eventParts[2].replace("to", "").trim());
                taskList.addTask(newEvent);
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
