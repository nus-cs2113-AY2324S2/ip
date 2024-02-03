import java.util.Scanner;

public class BossMan {
    private static final String SEP = "____________________________________________________________";
    private final Scanner SCANNER;
    private final TaskList TASK_LIST;

    public BossMan() {
        this.SCANNER = new Scanner(System.in);
        this.TASK_LIST = new TaskList();
    }

    public void greetUser() {
        System.out.println(SEP + "\nHello! I'm BossMan");
        System.out.println("What can I do for you?\n" + SEP);
    }

    public void endChat() {
        System.out.println("Bye. Hope to see you again soon!\n" + SEP);
    }

    public void startChat() {
        String userInput;

        do {
            System.out.print("You: ");
            userInput = SCANNER.nextLine();

            processUserInput(userInput);

        } while (!userInput.equalsIgnoreCase("bye"));
    }

    private void processUserInput(String userInput) {
        String[] parts = parseUserInput(userInput);

        if (parts.length == 0) {
            System.out.println("Unknown command\n" + SEP);
            return;
        }

        String commandType = parts[0];
        String commandArgs = parts.length > 1 ? parts[1] : "";

        switch (commandType.toLowerCase()) {
        case "mark":
            handleMarkCommand(commandArgs);
            break;

        case "unmark":
            handleUnmarkCommand(commandArgs);
            break;

        case "list":
            TASK_LIST.printTasks();
            break;

        case "todo":
            handleTodoCommand(commandArgs);
            break;

        case "deadline":
            handleDeadlineCommand(commandArgs);
            break;

        case "event":
            handleEventCommand(commandArgs);
            break;

        case "bye":
            break; // No need to print unknown command for 'bye'

        default:
            System.out.println("Unknown command\n" + SEP);
        }
    }

    private String[] parseUserInput(String userInput) {
        return userInput.trim().split("\\s+", 2);
    }

    private void handleMarkCommand(String commandArgs) {
        try {
            int number = Integer.parseInt(commandArgs);
            TASK_LIST.markTask(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format\n" + SEP);
        }
    }

    private void handleUnmarkCommand(String commandArgs) {
        try {
            int number = Integer.parseInt(commandArgs);
            TASK_LIST.unmarkTask(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format\n" + SEP);
        }
    }

    private void handleTodoCommand(String commandArgs) {
        Task todoTask = new Todo(commandArgs);
        TASK_LIST.addTask(todoTask);
        echo(todoTask);
    }

    private void handleDeadlineCommand(String commandArgs) {
        String[] deadlineArgParts = commandArgs.split("/by", 2);
        if (deadlineArgParts.length == 2) {
            String description = deadlineArgParts[0];
            String deadline = deadlineArgParts[1];
            Task deadlineTask = new Deadline(description, deadline);
            TASK_LIST.addTask(deadlineTask);
            echo(deadlineTask);
        } else {
            System.out.println("Invalid deadline command format\n" + SEP);
        }
    }

    private void handleEventCommand(String commandArgs) {
        String[] eventArgParts = commandArgs.split("/from", 2);
        if (eventArgParts.length == 2) {
            String description = eventArgParts[0];
            String[] eventArgTimeParts = eventArgParts[1].split("/to", 2);
            String from = eventArgTimeParts[0];
            String to = eventArgTimeParts[1];
            Task eventTask = new Event(description, from, to);
            TASK_LIST.addTask(eventTask);
            echo(eventTask);
        } else {
            System.out.println("Invalid event command format\n" + SEP);
        }
    }

    private void echo(Task task) {
        System.out.print("Added:");
        task.printTask();
        System.out.println();
        System.out.println("Now you have " + TASK_LIST.getTaskListSize() + " tasks in the list.");
        System.out.println(SEP);
    }
}






