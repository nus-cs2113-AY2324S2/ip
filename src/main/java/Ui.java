import java.util.Scanner;

/**
 * Helper User interface class to print results and command prompts to the command line interface that users of CheeseBot
 * interacts with.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DIVIDER = "\t-------------------------------------------------------------------";

    public void printDivider() {
        System.out.println(DIVIDER);
    }
    public void printGreeting() {
        printDivider();
        String greeting = "\tHello! I'm CheeseBot\n" + "\tWhat can I do for you?";
        System.out.println(greeting);
    }

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
}
