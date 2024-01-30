import java.util.Scanner;

public class Duke {
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

        // List items
        Task[] listOfTasks = new Task[100];
        int taskCount = 0;

        // Chat input and response Loop
        while (true) {
            userInput = in.nextLine().trim();

            if (isMarkUnmarkTask(userInput)) {
                handleMarkUnmarkTask(userInput, listOfTasks, taskCount);
                continue;
            }

            switch (userInput.toLowerCase()) {
            case "":
                continue;
            case "bye":
                return;
            case "list":
                echoListItems(listOfTasks, taskCount);
                break;
            default:
                addTaskToList(userInput, listOfTasks, taskCount);
                taskCount++;
                break;
            }
        }
    }

    private static boolean isMarkUnmarkTask(String userInput) {
        if (userInput.toLowerCase().startsWith("mark")
                || userInput.toLowerCase().startsWith("unmark")) {
            return true;
        }
        return false;
    }

    private static void handleMarkUnmarkTask(String userInput, Task[] listOfTasks, int taskCount) {
        String command = userInput.toLowerCase().startsWith("mark") ? "mark" : "unmark";
        String numberPart = userInput.substring(command.length()).trim();
        try {
            int taskIndex = Integer.parseInt(numberPart);
            changeTaskStatus(command.equals("mark"), taskIndex, listOfTasks);
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_DIVIDER);
            System.out.println("ERROR: Invalid task index, usage \"" + command + " (taskIndex)\"");
            System.out.println(MESSAGE_DIVIDER);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_DIVIDER);
            System.out.println("ERROR: Index out of bounds, usage \"" + command + " (taskIndex)\"");
            System.out.println(MESSAGE_DIVIDER);
        }
    }

    private static void changeTaskStatus(boolean checkmark, int taskIndex, Task[] listOfTasks) {
        // listOfTasks is indexed from 0
        listOfTasks[taskIndex - 1].setStatus(checkmark);

        System.out.println(MESSAGE_DIVIDER);
        if (checkmark == true) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        listOfTasks[taskIndex - 1].printTaskInfo();
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void addTaskToList(String userInput, Task[] listOfTasks, int index) {
        listOfTasks[index] = new Task(userInput);

        System.out.println(MESSAGE_DIVIDER);
        System.out.println("added: " + userInput);
        System.out.println(MESSAGE_DIVIDER);
    }

    private static void echoListItems(Task[] listOfTasks, int taskCount) {
        System.out.println(MESSAGE_DIVIDER);
        if (taskCount == 0) {
            System.out.println("No tasks added");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; listOfTasks[i] != null; i++) {
                System.out.printf("%d. [%s] %s%n", i+1, listOfTasks[i].getStatusIcon(), listOfTasks[i].description);
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
