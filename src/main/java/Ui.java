import Tasks.Task;
import java.util.Scanner;

/**
 * Helper User interface class to print results and command prompts to the command line interface that users of CheeseBot
 * interacts with.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DIVIDER = "\t-------------------------------------------------------------------";

    /**
     * Prints out a line of dashes to the command-line interface. Used to make the screen neater.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the greeting when CheeseBot is booted up. Used to welcome the user.
     */
    public void printGreeting() {
        printDivider();
        String greeting = "\tHello! I'm CheeseBot\n" + "\tWhat can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Prints out the farewell when user types in "bye" to end session. Used to indicate end of session.
     */
    public void printFarewell() {
        String farewell = "\tBye. Hope to see you again soon!";
        System.out.println(farewell);
        printDivider();
    }

    /**
     * Prints the list of commands available to the user. Invoked when the "help" command is supplied by the user.
     */
    public void printCommandList() {
        System.out.println("\tAvailable commands: help, todo, deadline, event, list, delete, find, mark, unmark, " +
                "help, bye\n");

        System.out.println("\tCommand 'help' usage: help\n" +
                "\tFunction: Lists the available command, along with its usage and functionality.\n");

        System.out.println("\tCommand 'todo' usage: todo <task name>\n" +
                "\tFunction: Adds a to do task with no deadline.\n" +
                "\tExample usage: todo Revise lecture 1 content\n");

        System.out.println("\tCommand 'deadline' usage: deadline <task name> /by <deadline>\n" +
                "\tFunction: Adds a to do task with a deadline.\n" +
                "\tDeadline format: dd/MM/yyyy HHmm\n" +
                "\tExample usage: deadline Submit iP /by 8/3/2024 2359\n");

        System.out.println("\tCommand 'list' usage: list\n" +
                "\tFunction: Lists all recorded tasks.\n" +
                "\tTasks' task numbers are obtained this way.\n");

        System.out.println("\tCommand 'delete' usage: delete <task number>\n" +
                "\tFunction: Deletes a specified task from the list\n" +
                "\tExample usage: delete 3\n");

        System.out.println("\tCommand 'event' usage: event <event name> /from <start time> /to <end time>\n" +
                "\tFunction: Adds an event with the start and end timings.\n" +
                "\tStart / End time format: dd/MM/yyyy HHmm\n" +
                "\tExample usage: event lecture /from 8/3/2024 1600 /to 8/3/2024 1800\n" +
                "\tNote: START_TIME cannot be after or the same as END_TIME\n");

        System.out.println("\tCommand 'find' usage: find <keyword>\n" +
                "\tFunction: Searches the list of tasks and prints task names that contains the keyword specified.\n" +
                "\tExample usage: find apple\n");

        System.out.println("\tCommand 'mark' usage: mark <task number>\n" +
                "\tFunction: Marks <task number> as completed.\n" +
                "\tExample usage: mark 1\n");

        System.out.println("\tCommand 'unmark' usage: unmark <task number>\n" +
                "\tFunction: Marks <task number> as not completed.\n" +
                "\tExample usage: unmark 1\n");

        System.out.println("\tCommand 'bye' usage: bye\n" +
                "\tFunction: Ends session with CheeseBot.");
    }

    /**
     * Prints an input prompt to ask user to type into the command line interface. Then waits for user to type a
     * command, then return that input line.
     *
     * @return The string input by the user.
     */
    public String printInputPrompt() {
        String inputPrompt = "\tPlease input your desired action or type 'help' for a list of commands: ";
        System.out.println(inputPrompt);
        return SCANNER.nextLine().strip();
    }

    /**
     * Invoked when a task is added into the array of tasks. Prints to screen the task added and the updated number of
     * tasks stored.
     *
     * @param taskName Name of the task added
     * @param numberOfTasks Latest number of tasks in array
     */
    public void printAdded(String taskName, int numberOfTasks) {
        System.out.println("\tYou have added: " + taskName);
        System.out.println("\tYou have a total of " + numberOfTasks + " completed and uncompleted tasks.");
    }

    /**
     * Invoked when a task is deleted from the array of tasks. Prints to screen the task deleted and the updated number
     * of tasks stored.
     * @param deletedTask Task that was deleted
     * @param numberOfTasks Latest number of tasks in array
     */
    public void printDeleted(Task deletedTask, int numberOfTasks) {
        System.out.println("\tDone! I have deleted the following task.");
        System.out.println("\t\t" + deletedTask);
        System.out.println("\tNow you have " + numberOfTasks + " tasks in your list");
    }
}
