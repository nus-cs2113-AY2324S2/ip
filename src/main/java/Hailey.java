import java.util.Scanner;

public class Hailey {
    private TaskManager taskManager;

    public Hailey() {
        this.taskManager = new TaskManager("./data/tasks.txt");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine().trim();
            printLine();

            if (userInput.equalsIgnoreCase("bye")) {
                printGoodbyeMessage();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                taskManager.printTasks();
            } else if (userInput.startsWith("todo")) {
                taskManager.addTodoTask(userInput.substring("todo".length()).trim());
            } else if (userInput.startsWith("deadline")) {
                // Parse deadline command and add deadline task
                String[] parts = userInput.split("/by", 2);
                if (parts.length != 2) {
                    printErrorMessage("Invalid deadline format. Please use: deadline <description> /by <due date>");
                } else {
                    String description = parts[0].substring("deadline".length()).trim();
                    String by = parts[1].trim();
                    taskManager.addDeadlineTask(description, by);
                }
            } else if (userInput.startsWith("event")) {
                // Parse event command and add event task
                String[] parts = userInput.split("/from", 2);
                if (parts.length != 2) {
                    printErrorMessage("Invalid event format. Please use: event <description> /from <start time> /to <end time>");
                } else {
                    String description = parts[0].substring("event".length()).trim();
                    String[] timeParts = parts[1].split("/to", 2);
                    if (timeParts.length != 2) {
                        printErrorMessage("Invalid event format. Please use: event <description> /from <start time> /to <end time>");
                    } else {
                        String from = timeParts[0].trim();
                        String to = timeParts[1].trim();
                        taskManager.addEventTask(description, from, to);
                    }
                }
            } else if (userInput.startsWith("delete")) {
                // Parse delete command and delete task
                String indexStr = userInput.substring("delete".length()).trim();
                try {
                    int index = Integer.parseInt(indexStr);
                    taskManager.deleteTask(index - 1); // Adjust index to 0-based
                } catch (NumberFormatException e) {
                    printErrorMessage("Invalid index for delete command. Please enter a valid task number.");
                }
            } else {
                printErrorMessage("I'm sorry, but I don't know what that means :-(");
            }
        }

        scanner.close();
    }

    private void printWelcomeMessage() {
        System.out.println("Hello! I'm Hailey");
        System.out.println("What can I do for you?");
        printLine();
    }

    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    private void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private void printErrorMessage(String message) {
        System.out.println("OOPS!!! " + message);
        printLine();
    }

    public static void main(String[] args) {
        new Hailey().run();
    }
}