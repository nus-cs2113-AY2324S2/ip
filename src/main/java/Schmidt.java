import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class for Schmidt, a multi-functional chatbot
 */
public class Schmidt {
    /**
     * This is the main method which makes use of Schmidt class.
     * @param args Unused.
     */
    public static void main(String[] args) {
        String LOGO = "░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗\n" +
                "██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝\n" +
                "╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░\n" +
                "░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░\n" +
                "██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░\n" +
                "╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░";
        String HELLO_MESSAGE = "------------------------------------------------------------\n" +
                " Hello! I'm Schmidt\n" +
                " What can I do for you?\n" +
                "------------------------------------------------------------";
        ArrayList<Task> tasks = new ArrayList<Task>();

        System.out.println("Hello from\n" + LOGO);

        System.out.println(HELLO_MESSAGE);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\t-> ");

            String input = sc.nextLine();
            String command = input.split(" ")[0];

            // Switch statement to handle different commands
            switch (command) {
            case "bye":
                printByeMessage();
                return;
            case "list":
                printListHelper(tasks);
                continue;
            case "mark":
                markTaskHelper(tasks, input, true);
                continue;
            case "unmark":
                markTaskHelper(tasks, input, false);
                continue;
            case "todo":
                addTodoHelper(tasks, input);
                continue;
            case "deadline":
                addDeadlineHelper(tasks, input);
                continue;
            case "event":
                addEventHelper(tasks, input);
                continue;
            default:
                printValidCommands();
                continue;
            }
        }
    }

    /**
     * This is a function to print the valid commands
     */
    public static void printValidCommands() {
        System.out.println("------------------------------------------------------------\n" +
                " I'm sorry, but the valid commands are:\n" +
                "\tbye\n" +
                "\tlist\n" +
                "\ttodo <description>\n" +
                "\tdeadline <description> /by <date>\n" +
                "\tevent <description> /from <date> /to <date>\n" +
                "\tmark <task number>\n" +
                "\tunmark <task number>\n" +
                "------------------------------------------------------------");
    }

    /**
     * This is a function to print the bye message
     */
    public static void printByeMessage() {
        System.out.println("------------------------------------------------------------\n" +
                " Bye. Hope to see you again soon!\n" +
                "------------------------------------------------------------");
    }

    /**
     * This is a helper method to print the list of tasks
     * @param tasks The list of tasks
     */
    public static void printListHelper(ArrayList<Task> tasks) {
        System.out.println("------------------------------------------------------------\n" +
                " Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
        }

        System.out.println("------------------------------------------------------------");
    }

    /**
     * This is a helper method to print the message after adding a task
     * @param tasks The list of tasks
     */
    public static void printAddedTaskMessage(ArrayList<Task> tasks) {
        System.out.println("------------------------------------------------------------\n" +
                "Got it. I've added this task:\n" +
                "\t" + tasks.get(tasks.size() - 1).toString() + "\n" +
                " Now you have " + tasks.size() + " tasks in the list.\n" +
                "------------------------------------------------------------");
    }

    /**
     * This is a helper method to add a todo task
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addTodoHelper(ArrayList<Task> tasks, String input) {
        try {
            String description = input.substring(5);
            tasks.add(new Todo(description));
        } catch (StringIndexOutOfBoundsException e) {
            printValidCommands();
        }

        printAddedTaskMessage(tasks);
    }

    /**
     * This is a helper method to add a deadline task
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addDeadlineHelper(ArrayList<Task> tasks, String input) {
        try {
            String description = input.substring(9, input.indexOf("/by") - 1);
            String by = input.substring(input.indexOf("/by") + 4);
            tasks.add(new Deadline(description, by));
        } catch (StringIndexOutOfBoundsException e) {
            printValidCommands();
        }

        printAddedTaskMessage(tasks);
    }

    /**
     * This is a helper method to add an event task
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addEventHelper(ArrayList<Task> tasks, String input) {
        try {
            String description = input.substring(6, input.indexOf("/from") - 1);
            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
            String to = input.substring(input.indexOf("/to") + 4);
            tasks.add(new Event(description, from, to));
        } catch (StringIndexOutOfBoundsException e) {
            printValidCommands();
        }

        printAddedTaskMessage(tasks);
    }

    /**
     * This is a helper method to mark or unmark a task as done
     * @param tasks The list of tasks
     * @param input The user input
     * @param shouldMark A boolean to indicate if the task needs to be marked or unmarked as done
     */
    public static void markTaskHelper(ArrayList<Task> tasks, String input, boolean shouldMark) {
        String[] tokens = input.split(" ");

        if (tokens.length != 2) {
            System.out.println("------------------------------------------------------------\n" +
                    " Please specify the task number to mark as done\n" +
                    "------------------------------------------------------------");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("------------------------------------------------------------\n" +
                    " Please specify the task number to mark as done\n" +
                    "------------------------------------------------------------");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            System.out.println("------------------------------------------------------------\n" +
                    " Task number out of range\n" +
                    "------------------------------------------------------------");
            return;
        }

        if (shouldMark) {
            tasks.get(index).markAsDone();

            System.out.println("------------------------------------------------------------\n" +
                    " Nice! I've marked this task as done:\n" +
                    "   " + tasks.get(index) + "\n" +
                    "------------------------------------------------------------");
        } else {
            tasks.get(index).unmarkAsDone();

            System.out.println("------------------------------------------------------------\n" +
                    " Nice! I've unmarked this task as done:\n" +
                    "   " + tasks.get(index) + "\n" +
                    "------------------------------------------------------------");
        }
    }
}
