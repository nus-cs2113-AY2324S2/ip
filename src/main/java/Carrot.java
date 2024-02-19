import java.util.Scanner;
import java.util.ArrayList;

public class Carrot {
    public static void main(String[] args) {
        String logo = "   _____                     _   \n" +
                        "  / ____|                   | |  \n" +
                        " | |     __ _ _ __ _ __ ___ | |_ \n" +
                        " | |    / _` | '__| '__/ _ \\| __|\n" +
                        " | |___| (_| | |  | | | (_) | |_ \n" +
                        "  \\_____\\__,_|_|  |_|  \\___/ \\__|\n";
        System.out.println("Hello from\n" + logo);

        // Chatbot functionality
        greetUser();
        printHelpCommand();
        handleUserInput();
    }

    private static ArrayList<Task> listOfTasks = Storage.loadListOfTasks();

    private static final String MESSAGE_DIVIDER = "-------------------------------------";
    private static final String COMMAND_LIST =
            "Available Commands:\n" +
                    "1. todo <taskdescription> - Add a new todo task\n" +
                    "2. deadline <taskdescription> /<by> - Add a new task with a deadline <by>\n" +
                    "3. event <taskdescription> /<from> /<to> - Add a new task that starts <from> and ends <to>\n" +
                    "4. mark <taskindex> - Mark a task as done\n" +
                    "5. unmark <taskindex> - Mark a task as not done\n" +
                    "6. delete <taskindex> - delete a task from the list\n" +
                    "7. list - List all tasks recorded\n" +
                    "8. bye - Exit the program\n" +
                    "9. help - Show available commands";

    private static void greetUser() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Hello! I'm Carrot!");
        System.out.println("What can I do for you?");
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();
            CommandType userCommand = CommandManager.getCommandType(userInput);

            processUserCommand(userCommand, userInput, scanner);
        }
    }

    private static void processUserCommand(CommandType userCommand, String userInput, Scanner scanner) {
        switch (userCommand) {
        case HELP:
            printHelpCommand();
            break;
        case LIST:
            printListItems();
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            addTaskToList(userCommand, userInput);
            break;
        case DELETE:
            deleteTaskFromList(userInput);
            break;
        case MARK:
        case UNMARK:
            handleMarkUnmarkTask(userCommand, userInput);
            break;
        case EXIT:
            scanner.close();
            sayGoodbye();
            System.exit(0);
            return;
        default:
        case INVALID:
            printInvalidCommand();
            break;
        }
    }

    private static void addTaskToList(CommandType command, String userInput) {
        String[] commandArguments =
                CommandManager.getCommandArguments(command, userInput);
        Task task;

        switch (command) {
        case TODO:
            task = new Todo(commandArguments[0]);
            break;
        case DEADLINE:
            task = new Deadline(commandArguments[0], commandArguments[1]);
            break;
        case EVENT:
            task = new Event(commandArguments[0], commandArguments[1], commandArguments[2]);
            break;
        default:
            return;
        }

        listOfTasks.add(task);
        Storage.writeAllTasksToStorage(listOfTasks);
        printAddedTask(task);
    }

    private static void deleteTaskFromList(String userInput) {
        String taskIndex =
                CommandManager.getCommandArguments(CommandType.DELETE, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex) - 1;

        try {
            Task taskToRemove = listOfTasks.get(taskIndexInt);
            listOfTasks.remove(taskIndexInt);
            Storage.writeAllTasksToStorage(listOfTasks);

            printDeletedTask(taskToRemove);
        } catch (Exception e) {
            printInvalidTaskIndexError();
        }
    }

    private static void handleMarkUnmarkTask(CommandType command, String userInput) {
        boolean isDone = (command == CommandType.MARK);

        String taskIndex =
                CommandManager.getCommandArguments(command, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex);

        try {
            changeTaskStatus(isDone, taskIndexInt);
        } catch (Exception e) {
            printInvalidTaskIndexError();
        }
    }

    private static void changeTaskStatus(boolean isDone, int taskIndex) {
        // listOfTasks is indexed from 0.
        // taskIndex is indexed from 1, as how the user sees the list is ordered
        Task task = listOfTasks.get(taskIndex - 1);

        task.setStatus(isDone);
        Storage.writeAllTasksToStorage(listOfTasks);
        printChangedTaskStatus(isDone, task);
    }

    private static void printHelpCommand() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println(COMMAND_LIST);
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void printInvalidCommand() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Invalid command");
        System.out.println("Type \"help\" to view available commands and syntax");
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void printInvalidTaskIndexError() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("ERROR: Invalid task index. " +
                "Type \"list\" to see available task indices\n" +
                "Usage: \"delete/mark/unmark <taskIndex>\"");
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void printChangedTaskStatus(boolean isDone, Task task) {
        System.out.println(MESSAGE_DIVIDER);
        if (isDone == true) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("\t" + task);
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void printAddedTask(Task task) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d task(s) in the list.%n", listOfTasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void printDeletedTask(Task task) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d task(s) in the list.%n", listOfTasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void printListItems() {
        int numberOfTasks = listOfTasks.size();

        System.out.println(MESSAGE_DIVIDER);
        if (numberOfTasks == 0) {
            System.out.println("No tasks added");
        } else {
            System.out.println("Here are the task(s) in your list:");
            for (int i = 1; i <= numberOfTasks; i++) {
                System.out.println(i + "." + listOfTasks.get(i - 1));
            }
        }
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void sayGoodbye() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(MESSAGE_DIVIDER);
    }
}
