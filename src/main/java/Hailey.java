import java.util.Scanner;

public class Hailey {
    private final TaskManager taskManager;

    public Hailey() {
        taskManager = new TaskManager();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine().trim();
            printLine();

            try {
                processUserInput(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            printLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
        }

        scanner.close();
    }

    private void processUserInput(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);
        String command = inputParts[0].toLowerCase();
        String argument = inputParts.length > 1 ? inputParts[1] : "";

        switch (command) {
            case "todo":
                taskManager.addTodoTask(argument);
                break;
            case "deadline":
                String[] deadlineArgs = argument.split("/by", 2);
                if (deadlineArgs.length != 2) {
                    throw new DukeException("OOPS!!! Please provide a description and deadline for the task.");
                }
                taskManager.addDeadlineTask(deadlineArgs[0].trim(), deadlineArgs[1].trim());
                break;
            case "event":
                String[] eventArgs = argument.split("/from", 2);
                if (eventArgs.length != 2) {
                    throw new DukeException("OOPS!!! Please provide a description and time period for the event.");
                }
                String[] eventTimeArgs = eventArgs[1].split("/to", 2);
                if (eventTimeArgs.length != 2) {
                    throw new DukeException("OOPS!!! Please provide a valid time period for the event.");
                }
                taskManager.addEventTask(eventArgs[0].trim(), eventTimeArgs[0].trim(), eventTimeArgs[1].trim());
                break;
            case "list":
                taskManager.listTasks();
                break;
            case "done":
                int taskIndex = Integer.parseInt(argument) - 1;
                taskManager.markTaskAsDone(taskIndex);
                break;
            case "delete":
                int taskIndexToDelete = Integer.parseInt(argument) - 1;
                taskManager.deleteTask(taskIndexToDelete);
                break;
            case "find":
                taskManager.findTasks(argument);
                break;
            case "bye":
                printGoodbyeMessage();
                return;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Hello! I'm Hailey");
        System.out.println("What can I do for you?");
    }

    private void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        new Hailey().run();
    }
}