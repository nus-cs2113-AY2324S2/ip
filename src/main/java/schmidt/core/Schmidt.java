package schmidt.core;

import schmidt.exception.SchmidtException;
import schmidt.task.Deadline;
import schmidt.task.Event;
import schmidt.task.Task;
import schmidt.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class for Schmidt, a multi-functional chatbot
 */
public class Schmidt {
    private static final String LOGO = "░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗\n" +
            "██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝\n" +
            "╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░\n" +
            "░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░\n" +
            "██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░\n" +
            "╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░";
    private static final String LINE = "------------------------------------------------------------";
    private static final String HELP_MESSAGE = "I'm sorry, but the valid commands are:\n" +
            "\tbye\n" +
            "\tlist\n" +
            "\ttodo <description>\n" +
            "\tdeadline <description> /by <date>\n" +
            "\tevent <description> /from <date> /to <date>\n" +
            "\tmark <task number>\n" +
            "\tunmark <task number>\n" +
            "\tdelete <task number>";
    private static final String STORAGE = "data/schmidt.txt";

    /**
     * This is the main method which makes use of Schmidt class.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        System.out.println(LOGO);
        print("Hello! I'm Schmidt");

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            pullFromStorage(tasks);
        } catch (SchmidtException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Saved tasks are corrupted\n" +
                    "Starting with an empty list of tasks");
            tasks = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find any saved tasks\n" +
                    "Starting with an empty list of tasks");
        }

        if (tasks.isEmpty()) {
            System.out.println("Starting with an empty list of tasks");
        } else {
            printListHelper(tasks, false);
        }

        print("What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            // push tasks to storage
            pushToStorage(tasks);

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
                printListHelper(tasks, true);
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
            case "delete":
                try {
                    deleteTaskHelper(tasks, trimmedInput);
                } catch (NumberFormatException e) {
                    print("The task number should be a number\n" +
                            "\tdelete <task number>");
                } catch (IndexOutOfBoundsException e) {
                    print("Task number out of range\n" +
                            "\tdelete <task number>");
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
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * This is a function to print the valid commands
     */
    public static void printValidCommands() {
        print(HELP_MESSAGE);
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
    public static void printListHelper(ArrayList<Task> tasks, boolean withLine) {
        if (tasks.isEmpty()) {
            print("You have no tasks in the list");
            return;
        }

        if (withLine) {
            System.out.println(LINE);
        }
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }

        if (withLine) {
            System.out.println(LINE);
        }
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
        } catch (ArrayIndexOutOfBoundsException e) {
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
        } catch (ArrayIndexOutOfBoundsException e) {
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

    /**
     * This is a helper method to delete a task
     *
     * @param tasks The list of tasks
     * @param input The user input
     */
    public static void deleteTaskHelper(ArrayList<Task> tasks, String input) throws SchmidtException {
        String[] tokens = input.split("\\s+");

        // incorrectly formatted command
        if (tokens.length < 2) {
            throw new SchmidtException("Please specify the task number to delete\n" +
                    "\tdelete <task number>");
        } else if (tokens.length > 2) {
            throw new SchmidtException("Please specify one task number to delete\n" +
                    "\tdelete <task number>");
        }

        int index = Integer.parseInt(tokens[1]) - 1;

        Task removedTask = tasks.remove(index);

        print("Noted. I've removed this task:\n" +
                "\t" + removedTask + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * This is a helper method to pull tasks from storage
     *
     * @param tasks The list of tasks
     */
    public static void pullFromStorage(ArrayList<Task> tasks) throws FileNotFoundException, SchmidtException {
        File file = new File(STORAGE);

        Scanner scanner = new Scanner(file);
        boolean isFileEmpty = true;
        while (scanner.hasNextLine()) {
            isFileEmpty = false;
            String line = scanner.nextLine();
            String[] tokens = line.split(" \\| ");

            switch (tokens[0]) {
            case "T":
                Todo todo = new Todo(tokens[2]);
                if (tokens[1].equals("1")) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(tokens[2], tokens[3]);
                if (tokens[1].equals("1")) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event(tokens[2], tokens[4], tokens[5]);
                if (tokens[1].equals("1")) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                throw new SchmidtException("Saved tasks are corrupted");
            }
        }
    }

    /**
     * This is a helper method to push tasks to storage
     *
     * @param tasks The list of tasks
     */
    public static void pushToStorage(ArrayList<Task> tasks) {
        try {
            File file = new File(STORAGE);
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                // type of task
                String taskType = "";
                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Deadline) {
                    taskType = "D";
                } else if (task instanceof Event) {
                    taskType = "E";
                }

                // status of task
                String isDone = "0";
                if (task.getStatus()) {
                    isDone = "1";
                }

                // description of task
                String description = task.getDescription();

                // by, from, to
                String by = "";
                if (task instanceof Deadline) {
                    by = ((Deadline) task).getBy();
                }

                String from = "";
                String to = "";
                if (task instanceof Event) {
                    from = ((Event) task).getFrom();
                    to = ((Event) task).getTo();
                }

                fileWriter.write(taskType + " | " + isDone + " | " + description + " | " + by + " | " + from + " | " + to + "\n");
            }
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred while saving tasks");
        }
    }
}
