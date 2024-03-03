import java.util.ArrayList;

/**
 * Represents the user interface of the Jarvas bot.
 */
public class Reply {
    public static final String PARTITION_LINE = "____________________________________________________________";

    // Input Errors
    public static final String INVALID_COMMAND = "Invalid command. Enter 'help' to view available commands.";
    public static final String UNSPECIFIED_PARAMETER = "Parameter is unspecified.";
    public static final String INVALID_PARAMETER = "Parameter is invalid and out of bounds";

    // List Errors
    public static final String EMPTY_LIST = "List is empty.";
    public static final String NO_RESULTS = "There are no results that match your search query.";

    // Storage Replies
    public static final String SAVE_ERROR = "File save failed.\nWrite error occurred:\n";
    public static final String MISSING_FILE = "Data file not found/corrupted. Starting with an empty list.";
    public static final String LOAD_ERROR = "File read error:\n" + "Error at task number = ";
    public static final String CORRUPT_ERROR = "\nFile is corrupted. Ceasing any further data imports.";
    public static final String SUCCESSFUL_LOAD = "Prior data file found\n" + "Previous data has been imported.";



    /**
     * Prints a horizontal line.
     */
    public static void printLine() {
        System.out.println(PARTITION_LINE);
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        printLine();
        System.out.println("Commands List:" + "\n");
        System.out.println("list - prints out the List");
        System.out.println("help - procures command list");
        System.out.println("bye - terminates the bot");
        printLine();
        System.out.println("todo - adds an item to the List");
        System.out.println("event - adds an event to the List");
        System.out.println("deadline - adds a deadline to the List");
        System.out.println("mark - indicates an item on the List as done");
        System.out.println("unmark - indicates an item on the List as not done");
        System.out.println("delete - deletes a task from the List");
        System.out.println("find - searches for a task from the List containing the keyword");
        printLine();
        System.out.println("todo format: todo *parameter*");
        System.out.println("event format: event *parameter* /from *start time* /to *end time*");
        System.out.println("deadline format: deadline *parameter* /by *end time*");
        System.out.println("unmark format: unmark *index*");
        System.out.println("mark format: mark *index*");
        System.out.println("delete format: delete *index*");
        System.out.println("find format: find *keyword*");
        printLine();
    }


    /**
     * Prints a formatted message containing the original user input sandwiched between two horizontal lines.
     *
     * @param reply The user input string to be formatted.
     */
    public static void printReply(String reply) {
        printLine();
        System.out.println(reply);
        printLine();
    }

    /**
     * Prints a formatted message containing the original user input sandwiched between two horizontal lines.
     * Prints two user input strings separated by a newline character
     *
     * @param firstString The first user input string to be formatted.
     * @param secondString The second user input string to be formatted.
     */
    public static void printReply(String firstString, String secondString) {
        printLine();
        System.out.println(firstString);
        System.out.println(secondString);
        printLine();
    }


    /**
     * Prints a formatted message to feedback a newly added task back to the user,
     * followed by the updated tally of the tasks list, sandwiched between two horizontal lines.
     *
     * @param task The {@code Task} object added to the tasks list.
     * @param total The total number of tasks in the tasks list.
     */
    public static void printReply(Task task, int total) {
        printLine();
        System.out.println("Got it. I've added: ");
        System.out.println(task);
        if (total == 1) {
            System.out.println("You now have " + total + " task in the list.");
        } else {
            System.out.println("You now have " + total + " tasks in the list.");
        }
        printLine();
    }

    /**
     * Prints an ASCII Art depicting the word 'Jarvas'.
     */
    public static void printArt() {
        System.out.println(" _____                                  ");
        System.out.println("(___  )                                 ");
        System.out.println("    | |   _ _  _ __  _   _    _ _   ___ ");
        System.out.println(" _  | | /'_` )( '__)( ) ( ) /'_` )/',__)");
        System.out.println("( )_| |( (_| || |   | \\_/ |( (_| |\\__, \\");
        System.out.println("`\\___/'`\\__,_)(_)   `\\___/'`\\__,_)(____/");
    }

    /**
     * Prints the startup message.
     */
    public static void printWelcomeMessage() {
        printArt();
        printReply("Hello! I'm Jarvas", "What can I do for you?");
    }
    /**
     * Prints the shutdown message.
     */
    public static void printGoodbyeMessage() {
        printReply("Have a good day!", "Bye, see you soon!");
    }

    /**
     * Prints out the error message if any.
     * @param e A string representing the error message to print.
     */
    public static void printException(CustomException e) {
        System.err.println("Custom Exception Caught!" + "\n" + e.getMessage());
        Reply.printLine();
    }

    /**
     * Prints out the error message if any.
     * @param e A string representing the error message to print.
     * @param input A string representing a custom error message to print.
     */
    public static void printException(Exception e, String input) {
        System.err.println("Custom Exception Caught!\n" + input + "\n\n" + e.getMessage());
        Reply.printLine();
    }

    /**
     * Prints out the search results.
     *
     * @param filteredList An ArrayList of {@code Task} to print containing the search results.
     */
    public static void printSearch(ArrayList<Task> filteredList) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        int taskIndex = 0;

        for (Task t : filteredList) {
            System.out.println((taskIndex + 1) + ". "  + filteredList.get(taskIndex));
            taskIndex++;
        }

        printLine();
    }

}
