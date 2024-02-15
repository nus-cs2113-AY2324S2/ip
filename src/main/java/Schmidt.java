import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class for Schmidt, a multi-functional chatbot
 */
public class Schmidt {
    /**
     * This is the main method which makes use of Schmidt class.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        String LOGO = "░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗\n" +
                "██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝\n" +
                "╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░\n" +
                "░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░\n" +
                "██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░\n" +
                "╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░";
        ArrayList<Task> tasks = new ArrayList<Task>();

        System.out.println(LOGO);
        print("Hello! I'm Schmidt\n" +
                "What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            // indent user input
            System.out.print("\t-> ");

            String input = sc.nextLine();

            // trim the input to remove leading and trailing whitespaces
            String trimmedInput = input.trim();

            // check if the input is empty
            String command;
            try {
                // get the command from the input and convert it to lowercase
                command = trimmedInput.split(" ")[0].toLowerCase();
            } catch (ArrayIndexOutOfBoundsException e) {
                printValidCommands();
                continue;
            }

            // Switch statement to handle different commands
            switch (command) {
            case "bye":
                printByeMessage();
                return;
            case "list":
                printListHelper(tasks);
                continue;
            case "mark":
                markTaskHelper(tasks, trimmedInput, true);
                continue;
            case "unmark":
                markTaskHelper(tasks, trimmedInput, false);
                continue;
            case "todo":
                addTodoHelper(tasks, trimmedInput);
                continue;
            case "deadline":
                addDeadlineHelper(tasks, trimmedInput);
                continue;
            case "event":
                addEventHelper(tasks, trimmedInput);
                continue;
            default:
                printValidCommands();
                continue;
            }
        }
    }

    /**
     * This is a function to add lines to print statements
     */
    public static void print(String message) {
        System.out.println("------------------------------------------------------------");
        System.out.println(message);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * This is a function to print the valid commands
     */
    public static void printValidCommands() {
        print("I'm sorry, but the valid commands are:\n" +
                "\tbye\n" +
                "\tlist\n" +
                "\ttodo <description>\n" +
                "\tdeadline <description> /by <date>\n" +
                "\tevent <description> /from <date> /to <date>\n" +
                "\tmark <task number>\n" +
                "\tunmark <task number>");
    }

    /**
     * This is a function to print the bye message
     */
    public static void printByeMessage() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * This is a helper method to print the list of tasks
     *
     * @param tasks The list of tasks
     */
    public static void printListHelper(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            print("You have no tasks in the list");
            return;
        }

        System.out.println("------------------------------------------------------------\n" +
                "Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }

        System.out.println("------------------------------------------------------------");
    }

    /**
     * This is a helper method to print the message after adding a task
     *
     * @param tasks The list of tasks
     */
    public static void printAddedTaskMessage(ArrayList<Task> tasks) {
        print("Got it. I've added this task:\n" +
                "\t" + tasks.get(tasks.size() - 1).toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * This is a helper method to add a todo task
     *
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addTodoHelper(ArrayList<Task> tasks, String input) {
        try {
            // parse the input to get the description
            String description = input.substring(5);
            tasks.add(new Todo(description));

            printAddedTaskMessage(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            printValidCommands();
        }
    }

    /**
     * This is a helper method to add a deadline task
     *
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addDeadlineHelper(ArrayList<Task> tasks, String input) {
        try {
            // parse the input to get the description and by
            String description = input.substring(9, input.indexOf("/by") - 1);
            String by = input.substring(input.indexOf("/by") + 4);
            tasks.add(new Deadline(description, by));

            printAddedTaskMessage(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            printValidCommands();
        }
    }

    /**
     * This is a helper method to add an event task
     *
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addEventHelper(ArrayList<Task> tasks, String input) {
        try {
            // parse the input to get the description, from and to
            String description = input.substring(6, input.indexOf("/from") - 1);
            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
            String to = input.substring(input.indexOf("/to") + 4);
            tasks.add(new Event(description, from, to));

            printAddedTaskMessage(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            printValidCommands();
        }
    }

    /**
     * This is a helper method to mark or unmark a task as done
     *
     * @param tasks      The list of tasks
     * @param input      The user input
     * @param shouldMark A boolean to indicate if the task needs to be marked or unmarked as done
     */
    public static void markTaskHelper(ArrayList<Task> tasks, String input, boolean shouldMark) {
        String[] tokens = input.split(" ");

        // incorrectly formatted command
        if (tokens.length != 2) {
            print("Please specify the task number to mark as done");
            return;
        }

        // task number is not a number
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            print("Please specify the task number to mark as done");
            return;
        }

        // task number out of range
        if (index < 0 || index >= tasks.size()) {
            print("Task number out of range");
            return;
        }

        // mark or unmark the task as done
        if (shouldMark) {
            tasks.get(index).markAsDone();

            print("Nice! I've marked this task as done:\n" +
                    "\t" + tasks.get(index));
        } else {
            tasks.get(index).unmarkAsDone();

            print("Nice! I've unmarked this task as done:\n" +
                    "\t" + tasks.get(index));
        }
    }
}
