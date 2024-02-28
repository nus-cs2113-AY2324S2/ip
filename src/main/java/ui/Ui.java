package carrot.ui;

import carrot.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String logo =
            "   _____                     _   \n" +
            "  / ____|                   | |  \n" +
            " | |     __ _ _ __ _ __ ___ | |_ \n" +
            " | |    / _` | '__| '__/ _ \\| __|\n" +
            " | |___| (_| | |  | | | (_) | |_ \n" +
            "  \\_____\\__,_|_|  |_|  \\___/ \\__|\n";

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

    private static Scanner scanner = new Scanner(System.in);

    /**
     * A method to get the instance of the Ui scanner
     * @return Scanner instance
     */
    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Greets the user and displays the welcome message.
     * <p>
     * This method prints a welcome message to the standard output, including the Carrot logo,
     * a greeting message, and a prompt for user input.
     */
    public static void greetUser() {
        System.out.println("Hello from\n" + logo);
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Hello! I'm Carrot!");
        System.out.println("What can I do for you?");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static String readUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints the help command and syntax to the standard output.
     * <p>
     * This method prints the list of available commands and their syntax to the standard output.
     */
    public static void printHelpCommand() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println(COMMAND_LIST);
        System.out.println(MESSAGE_DIVIDER);
    }

    /**
     * Prints a standard error message for an invalid command.
     * <p>
     * This method prints an error message to the standard output indicating
     * that the entered command is of invalid format.
     * <p>
     * It provides guidance on how to view available commands and their syntax by typing "help".
     */
    public static void printInvalidCommand() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Invalid command");
        System.out.println("Type \"help\" to view available commands and syntax");
        System.out.println(MESSAGE_DIVIDER);
    }

    /**
     * Prints an error message for an invalid task index.
     * <p>
     * This method prints an error message to the standard output indicating that the entered task index is not valid.
     * Non Valid indices are non integer values within the range of tasklist
     * <p>
     * It provides guidance on how to view available task indices by typing "list",
     * along with the correct usage syntax for commands requiring a task index.
     */
    public static void printInvalidTaskIndexError() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("ERROR: Invalid task index. " +
                "Type \"list\" to see available task indices\n" +
                "Usage: \"delete/mark/unmark <taskIndex>\"");
        System.out.println(MESSAGE_DIVIDER);
    }

    /**
     * Prints a message indicating the status change of a task.
     * <p>
     * This method prints a message to the standard output indicating the marked status of a specified task
     * The message includes the task details
     *
     * @param isDone a boolean flag indicating whether the task is marked as done
     * @param task   the task whose status has been changed
     */
    public static void printChangedTaskStatus(boolean isDone, Task task) {
        System.out.println(MESSAGE_DIVIDER);
        if (isDone == true) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("\t" + task);
        System.out.println(MESSAGE_DIVIDER);
    }

    /**
     * Prints a message confirming the addition of a task.
     * <p>
     * This method prints a message to the standard output indicating that a task has been added successfully.
     *
     * @param task       the task that has been added
     * @param listOfTasks the list of tasks after the addition
     */
    public static void printAddedTask(Task task, ArrayList<Task> listOfTasks) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d task(s) in the list.%n", listOfTasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    /**
     * Prints a message confirming the deletion of a task.
     * <p>
     * This method prints a message to the standard output indicating that a task has been deleted successfully.
     *
     * @param task       the task that has been deleted
     * @param listOfTasks the list of tasks after the deletion
     */
    public static void printDeletedTask(Task task, ArrayList<Task> listOfTasks) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d task(s) in the list.%n", listOfTasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    /**
     * Prints the list of tasks.
     * <p>
     * This method prints the list of tasks to the standard output, along with their indices.
     * If the list is empty, it prints a message indicating that no tasks have been added.
     *
     * @param listOfTasks the list of tasks to be printed
     */
    public static void printListItems(ArrayList<Task> listOfTasks) {
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

    /**
     * Prints an error message for failed input reading.
     * <p>
     * This method prints an error message to the standard output indicating that reading input has failed,
     * resulting in the program's termination.
     * <p>
     * It is typically used to handle scenarios such as reaching the end of input.
     */
    public static void printFindTaskOutput(ArrayList<Task> listOfTasks, String word) {
        int numberOfTasks = listOfTasks.size();

        System.out.println(MESSAGE_DIVIDER);
        if (numberOfTasks == 0) {
            System.out.println("No tasks added. Add a task before using \"find\" command");
        } else {
            boolean foundMatch = false;

            for (int i = 1; i <= numberOfTasks; i++) {
                String task = listOfTasks.get(i-1).toString().toLowerCase();
                String matchingWord = word.toLowerCase();

                if (task.contains(matchingWord)) {
                    if (!foundMatch) {
                        System.out.printf("Here are the task(s) that contains \"%s\" in your list:\n", word);
                        foundMatch = true;
                    }
                    System.out.println(i + "." + listOfTasks.get(i - 1));
                }
            }

            if (!foundMatch) {
                System.out.printf("There is no matching task that contains \"%s\"\n", word);
                System.out.println("Use \"list\" command to view all tasks available");
            }
        }
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printScannerInputError() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Failed to read input. Exiting Program...");
        System.out.println(MESSAGE_DIVIDER);
    }

    /**
     * Prints a farewell message.
     * <p>
     * This method prints a farewell message to the standard output, indicating the program's termination.
     */
    public static void sayGoodbye() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(MESSAGE_DIVIDER);
    }
}
