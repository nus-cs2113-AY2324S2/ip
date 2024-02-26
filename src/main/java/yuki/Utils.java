package yuki;

/**
 * Utility functions for printing message to user.
 */
public class Utils {
    public static void printLine() {
        System.out.println("---------------------------------------------");
    }

    /**
     * Greets user in command line.
     */
    public static void printWelcomeMessage() {
        printLine();
        System.out.println("I am Yuki, your personal chat bot and your evil twin.");
        System.out.println("Time to get grinding.");
        printLine();
    }

    /**
     * Prints message to user when exiting program.
     */
    public static void printExitMessage() {
        printLine();
        System.out.println("Breaks are only for the weak.");
        printLine();
    }

    /**
     * Prints message to user when new task has been added.
     */
    public static void printTaskAddedMessage() {
        System.out.println("new todo for you:");
    }

    /**
     * Prints list of accepted instructions.
     */
    public static void printInstructions() {
        System.out.println("Accepted commands are:");
        System.out.println("help: shows available commands\n");
        System.out.println("list: shows you list of tasks");
        System.out.println("\tInput Example: list\n");
        System.out.println("mark: marks a task as done");
        System.out.println("\tInput Example: mark 1\n");
        System.out.println("unmark: marks a task as undone");
        System.out.println("\tInput Example: unmark 1\n");
        System.out.println("todo: adds a todo");
        System.out.println("\tInput Example: todo read book\n");
        System.out.println("deadline: adds a deadline");
        System.out.println("\tInput Example: deadline wash clothes /by Sunday\n");
        System.out.println("event: adds an event");
        System.out.println("\tInput Example: event birthday party /from 1 Jan /to 4 Jan");
    }

    /**
     * Print warning when an unrecognised command is entered.
     */
    public static void printInvalidCommandWarning() {
        System.out.println("Invalid command.");
        printLine();
    }
}
