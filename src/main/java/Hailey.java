import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine().trim().toLowerCase();
            printLine();

            if (userInput.equals("bye")) {
                break;
            }

            String[] inputParts = userInput.split("\\s+", 2);
            String command = inputParts[0];
            String description = inputParts.length > 1 ? inputParts[1] : "";

            switch (command) {
                case "list":
                    taskManager.listTasks();
                    break;
                case "todo":
                    taskManager.addTask(new Todo(description));
                    break;
                case "deadline":
                    String[] deadlineParts = description.split("/by", 2);
                    taskManager.addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                    break;
                case "event":
                    String[] eventParts = description.split("/from", 2);
                    String[] eventDateTime = eventParts[1].split("/to", 2);
                    taskManager.addTask(new Event(eventParts[0].trim(), eventDateTime[0].trim(), eventDateTime[1].trim()));
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    printLine();
            }
        }

        printGoodbyeMessage();
        scanner.close();
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "What can I help you with?");
        printLine();
        System.out.println("Hello! I'm Hailey.\nWhat can I do for you?");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}