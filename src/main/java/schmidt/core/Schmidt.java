package schmidt.core;

import schmidt.exception.SchmidtException;
import schmidt.task.Deadline;
import schmidt.task.Event;
import schmidt.task.Task;
import schmidt.task.Todo;

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
        ArrayList<Task> tasks = new ArrayList<>();

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
                // split input by whitespace and extract the command
                command = trimmedInput.split("\\s+")[0].toLowerCase();
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
                try {
                    markTaskHelper(tasks, trimmedInput, true);
                } catch (NumberFormatException e) {
                    print("The task number should be a number\n" +
                            "\tmark <task number>");
                } catch (IndexOutOfBoundsException e) {
                    print("Task number out of range\n" +
                            "\tmark <task number>");
                } catch (SchmidtException e) {
                    print(e.getMessage());
                }
                continue;
            case "unmark":
                try {
                    markTaskHelper(tasks, trimmedInput, false);
                } catch (NumberFormatException e) {
                    print("The task number should be a number\n" +
                            "\tunmark <task number>");
                } catch (IndexOutOfBoundsException e) {
                    print("Task number out of range\n" +
                            "\tunmark <task number>");
                } catch (SchmidtException e) {
                    print(e.getMessage());
                }
                continue;
            case "todo":
                try {
                    addTodoHelper(tasks, trimmedInput);
                } catch (StringIndexOutOfBoundsException e) {
                    print("The description of a todo cannot be empty\n" +
                            "\ttodo <description>");
                }
                continue;
            case "deadline":
                try {
                    addDeadlineHelper(tasks, trimmedInput);
                } catch (SchmidtException e) {
                    print(e.getMessage());
                }
                continue;
            case "event":
                try {
                    addEventHelper(tasks, trimmedInput);
                } catch (SchmidtException e) {
                    print(e.getMessage());
                }
                continue;
            default:
                printValidCommands();
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
        if (tasks.isEmpty()) {
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
     * This is a helper method to add a Todo task
     *
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addTodoHelper(ArrayList<Task> tasks, String input) {
        // split by the first whitespace to get the description
        String[] inputTokens = input.split("\\s+", 2);
        if (inputTokens.length < 2) {
            print("The description of a todo cannot be empty\n" +
                    "\ttodo <description>");
            return;
        }

        String description = inputTokens[1];
        tasks.add(new Todo(description));

        printAddedTaskMessage(tasks);
    }

    /**
     * This is a helper method to add a deadline task
     *
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addDeadlineHelper(ArrayList<Task> tasks, String input) throws SchmidtException {
        // parse the input to get the description and by
        String[] inputTokens = input.split("\\s+/by\\s+");

        // no description or "/by"
        if (inputTokens.length < 2) {
            throw new SchmidtException("Please specify the deadline and description\n" +
                    "\tdeadline <description> /by <date>");
        }

        // no description
        String description;
        try {
            description = inputTokens[0].split("\\s+", 2)[1];
        } catch (StringIndexOutOfBoundsException e) {
            throw new SchmidtException("The description of a deadline cannot be empty\n" +
                    "\tdeadline <description> /by <date>");
        }

        String by = inputTokens[1];

        tasks.add(new Deadline(description, by));

        printAddedTaskMessage(tasks);
    }

    /**
     * This is a helper method to add an event task
     *
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void addEventHelper(ArrayList<Task> tasks, String input) throws SchmidtException {
        // split by "/from" to get the description and time details
        String[] inputSplitByFrom = input.split("\\s+/from\\s+");

        // no "/from" or "/to"
        if (inputSplitByFrom.length < 2) {
            throw new SchmidtException("Please specify both the from and to date\n" +
                    "\tevent <description> /from <date> /to <date>");
        }

        // split by "/to" to get the from and to
        String[] inputSplitByTo = inputSplitByFrom[1].split("\\s+/to\\s+");

        // no "/from" or "/to"
        if (inputSplitByTo.length < 2) {
            throw new SchmidtException("Please specify both the from and to date\n" +
                    "\tevent <description> /from <date> /to <date>");
        }

        String description;
        try {
            description = inputSplitByFrom[0].split("\\s+", 2)[1];
        } catch (StringIndexOutOfBoundsException e) {
            throw new SchmidtException("The description of an event cannot be empty\n" +
                    "\tevent <description> /from <date> /to <date>");
        }

        String from = inputSplitByTo[0].trim();
        String to = inputSplitByTo[1].trim();

        tasks.add(new Event(description, from, to));

        printAddedTaskMessage(tasks);
    }

    /**
     * This is a helper method to mark or unmark a task as done
     *
     * @param tasks      The list of tasks
     * @param input      The user input
     * @param shouldMark A boolean to indicate if the task needs to be marked or unmarked as done
     */
    public static void markTaskHelper(ArrayList<Task> tasks, String input, boolean shouldMark) throws SchmidtException {
        String[] tokens = input.split("\\s+");

        // incorrectly formatted command
        if (tokens.length < 2) {
            throw new SchmidtException("Please specify the task number to mark as done\n" +
                    "\tmark <task number>");
        } else if (tokens.length > 2) {
            throw new SchmidtException("Please specify one task number to mark as done\n" +
                    "\tmark <task number>");
        }

        int index = Integer.parseInt(tokens[1]) - 1;

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
