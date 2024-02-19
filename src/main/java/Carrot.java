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
            "6. list - List all tasks recorded\n" +
            "7. bye - Exit the program\n" +
            "8. help - Show available commands";

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
        case INVALID:
            printInvalidCommand();
            break;
        case HELP:
            printHelpCommand();
            break;
        case LIST:
            printListItems();
            break;
        case TODO:
            addTodoToList(userInput);
            break;
        case DEADLINE:
            addDeadlineToList(userInput);
            break;
        case EVENT:
            addEventToList(userInput);
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
        }
    }

    private static void addTodoToList(String userInput) {
        String[] todoCommandArguments =
                CommandManager.getCommandArguments(CommandType.TODO, userInput);

        String taskDescription =
                todoCommandArguments[0];

        Todo task = new Todo(taskDescription);

        listOfTasks.add(task);
        Storage.writeAllTasksToStorage(listOfTasks);
        printAddedTask(task);
    }

    private static void addDeadlineToList(String userInput) {
        String[] deadlineCommandArguments =
                CommandManager.getCommandArguments(CommandType.DEADLINE, userInput);

        String taskDescription =
                deadlineCommandArguments[0];
        String by =
                deadlineCommandArguments[1];

        Deadline task = new Deadline(taskDescription, by);

        listOfTasks.add(task);
        Storage.writeAllTasksToStorage(listOfTasks);
        printAddedTask(task);
    }

    private static void addEventToList(String userInput) {
        String[] eventCommandArguments =
                CommandManager.getCommandArguments(CommandType.EVENT, userInput);

        String taskDescription =
                eventCommandArguments[0];
        String from =
                eventCommandArguments[1];
        String to =
                eventCommandArguments[2];

        Event task = new Event(taskDescription, from, to);

        listOfTasks.add(task);
        Storage.writeAllTasksToStorage(listOfTasks);
        printAddedTask(task);
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
        System.out.println("ERROR: Invalid task index, usage \"mark/unmark <taskIndex>\"");
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
        System.out.printf("Now you have %d tasks in the list.%n", listOfTasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void printListItems() {
        int numberOfTasks = listOfTasks.size();

        System.out.println(MESSAGE_DIVIDER);
        if (numberOfTasks == 0) {
            System.out.println("No tasks added");
        } else {
            System.out.println("Here are the tasks in your list:");
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
