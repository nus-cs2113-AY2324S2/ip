import java.util.Scanner;

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
        handleUserInput();
        sayGoodbye();
    }

    private static Task[] listOfTasks = new Task[100];
    private static int taskCount = 0;

    private static final String MESSAGE_DIVIDER = "-------------------------------------";

    private static void greetUser() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Hello! I'm Carrot!");
        System.out.println("What can I do for you?");
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void handleUserInput() {
        // Scanner object for userInput
        Scanner in = new Scanner(System.in);

        String userInput;
        CommandType userCommand;

        // Chat input and response Loop
        while (true) {
            userInput = in.nextLine().trim();
            userCommand = CommandManager.getCommandType(userInput);

            switch (userCommand) {
            case EXIT:
                return;
            case LIST:
                echoListItems();
                break;
            case MARK:
            case UNMARK:
                handleMarkUnmarkTask(userCommand, userInput);
                break;
            default:
                addTaskToList(userInput);
                taskCount++;
                break;
            }
        }
    }

    private static void handleInvalidCommand() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Invalid command");
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void handleMarkUnmarkTask(CommandType command, String userInput) {
        boolean checkmark = (command == CommandType.MARK);

        String taskIndex = CommandManager.getCommandArguments(command, userInput)[0];
        int taskIndexInt = Integer.parseInt(taskIndex);

        try {
            changeTaskStatus(checkmark, taskIndexInt);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            handleInvalidTaskIndexError();
        }
    }

    private static void handleInvalidTaskIndexError() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("ERROR: Invalid task index, usage \"mark/unmark <taskIndex>\"");
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void changeTaskStatus(boolean checkmark, int taskIndex) {
        // listOfTasks is indexed from 0.
        // taskIndex is indexed from 1, as how the user sees the list is ordered
        listOfTasks[taskIndex - 1].setStatus(checkmark);

        System.out.println(MESSAGE_DIVIDER);
        if (checkmark == true) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(listOfTasks[taskIndex - 1]);
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void addTaskToList(String userInput) {
        listOfTasks[taskCount] = new Task(userInput);

        System.out.println(MESSAGE_DIVIDER);
        System.out.println("added: " + userInput);
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void echoListItems() {
        System.out.println(MESSAGE_DIVIDER);
        if (taskCount == 0) {
            System.out.println("No tasks added");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; listOfTasks[i] != null; i++) {
                System.out.println((i + 1) + ". " + listOfTasks[i]);
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
