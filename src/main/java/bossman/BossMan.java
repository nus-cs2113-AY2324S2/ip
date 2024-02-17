package bossman;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import bossman.task.Task;
import bossman.task.TaskList;
import bossman.task.Event;
import bossman.task.Todo;
import bossman.task.Deadline;
import bossman.exceptions.commandexceptions.InvalidEventCommandException;
import bossman.exceptions.commandexceptions.InvalidDeadlineCommandException;
import bossman.exceptions.commandexceptions.InvalidTodoCommandException;
import bossman.exceptions.commandexceptions.UnknownCommandException;


public class BossMan {
    private static final String SEP = "____________________________________________________________";
    private final Scanner SCANNER;
    private final TaskList TASK_LIST;
    private final Data data;

    public BossMan() throws FileNotFoundException {
        this.data = new Data();
        this.SCANNER = new Scanner(System.in);
        this.TASK_LIST = data.TASK_LIST;
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

            try {
                processUserInput(userInput);
            } catch (UnknownCommandException |
                     InvalidTodoCommandException |
                     InvalidDeadlineCommandException |
                     InvalidEventCommandException |
                     NumberFormatException e) {
                System.out.println(e.getMessage() + "\n" + SEP);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } while (!userInput.equalsIgnoreCase("bye"));
    }

    private void processUserInput(String userInput)
            throws UnknownCommandException,
            InvalidTodoCommandException,
            InvalidDeadlineCommandException,
            InvalidEventCommandException,
            NumberFormatException, IOException {

        String[] parts = parseUserInput(userInput);

        if (parts.length == 0) {
            throw new UnknownCommandException("Unknown command");
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
            data.saveTasksToFile();
            break;

        default:
            throw new UnknownCommandException("Unknown command");
        }
    }

    private String[] parseUserInput(String userInput) {
        return userInput.trim().split("\\s+", 2);
    }

    private void handleMarkCommand(String commandArgs) {
        int number = Integer.parseInt(commandArgs);
        TASK_LIST.markTask(number);
    }

    private void handleUnmarkCommand(String commandArgs) throws NumberFormatException {
        int number = Integer.parseInt(commandArgs);
        TASK_LIST.unmarkTask(number);
    }

    private void handleTodoCommand(String commandArgs) throws InvalidTodoCommandException {
        if(commandArgs.isBlank()) {
            throw new InvalidTodoCommandException("Invalid todo command");
        }
        Task todoTask = new Todo(commandArgs, false);
        TASK_LIST.addTask(todoTask);
        echo(todoTask);
    }

    private void handleDeadlineCommand(String commandArgs) throws InvalidDeadlineCommandException {
        String[] deadlineArgParts = commandArgs.split("/by", 2);
        if (deadlineArgParts.length != 2 || deadlineArgParts[0].isBlank()) {
            throw new InvalidDeadlineCommandException("Invalid deadline command");
        }
        String description = deadlineArgParts[0];
        String deadline = deadlineArgParts[1];
        Task deadlineTask = new Deadline(description, false, deadline);
        TASK_LIST.addTask(deadlineTask);
        echo(deadlineTask);
    }

    private void handleEventCommand(String commandArgs) throws InvalidEventCommandException {
        String[] eventArgParts = commandArgs.split("/from", 2);

        if (eventArgParts.length != 2 || eventArgParts[0].isBlank()) {
            throw new InvalidEventCommandException("Invalid event command");
        }

        String description = eventArgParts[0];
        String[] eventArgTimeParts = eventArgParts[1].split("/to", 2);

        if (eventArgTimeParts.length != 2) {
            throw new InvalidEventCommandException("Invalid event command");
        }

        String from = eventArgTimeParts[0];
        String to = eventArgTimeParts[1];
        Task eventTask = new Event(description, false, from, to);
        TASK_LIST.addTask(eventTask);
        echo(eventTask);
    }

    private void echo(Task task) {
        System.out.print("Added:");
        task.printTask();
        System.out.println();
        System.out.println("Now you have " + TASK_LIST.getTaskListSize() + " tasks in the list.");
        System.out.println(SEP);
    }
}