import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


/**
 * DavinciBot is a simple bot that allows the user to manage a to-do list.
 * The bot echoes commands entered by the user, adds tasks to the list,
 * marks tasks as done, and unmarks tasks.
 */
public class DavinciBot {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    public static final int SPLIT_INTO_TWO_PARTS = 2;
    private static final String DATA_FILE_PATH = "C:\\cs2113 individual project\\ip\\data\\DavinciBot.txt";
    public static Task[] taskArray = new Task[0];



    /**
     * Selects the icon corresponding to the type of task inputted by the user.
     *
     * @param taskArray Array of tasks.
     */
    private static void iconSelector(Task[] taskArray) {
        for (int i = 0; i < taskArray.length; i++) {
            String taskType = (taskArray[i] instanceof Deadline) ? "[D]" :
                    (taskArray[i] instanceof Todo) ? "[T]" : "[E]";

            System.out.println((i + 1) + ". " + taskType +
                    " [" + taskArray[i].getStatusIcon() + "] " +
                    taskArray[i].getDescription());
        }
    }
    /**
     * Displays the tasks in the user's to-do list.
     *
     * @param taskArray Array of tasks.
     */
    private static void displayTaskList(Task[] taskArray) {
        if (taskArray.length == 0) {
            System.out.println("No tasks entered yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            iconSelector(taskArray);
        }
    }

    /**
     * Marks a specified task as completed.
     *
     * @param userInput User input specifying the task.
     * @param taskArray Array of tasks.
     */
    private static void completeTask(String userInput, Task[] taskArray) {
        try {
            String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
            if (parts.length > 1) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskArray.length) {
                    taskArray[taskIndex].markAsDone();
                    System.out.println(LINE_SEPARATOR);
                    System.out.println("Nice job! I've marked this task as done :D");
                    System.out.println("[" + taskArray[taskIndex].getStatusIcon() + "] " +
                            taskArray[taskIndex].getDescription());
                    System.out.println(LINE_SEPARATOR);

                    writeFile();
                } else {
                    throw new DavinciException("Invalid task index.");
                }
            } else {
                throw new DavinciException("Please specify the task index to complete.");
            }
        } catch (DavinciException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task index format.");
        }
    }

    /**
     * Marks a specified task as not done.
     *
     * @param userInput User input specifying the task.
     * @param taskArray Array of tasks.
     */
    private static void unmarkTask(String userInput, Task[] taskArray) {
        try {
            String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
            if (parts.length > 1) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskArray.length) {
                    taskArray[taskIndex].markAsNotDone();
                    System.out.println(LINE_SEPARATOR);
                    System.out.println("OK, I've marked this task as not done, but stop being lazy!");
                    System.out.println("[" + taskArray[taskIndex].getStatusIcon() + "] " +
                            taskArray[taskIndex].getDescription());
                    System.out.println(LINE_SEPARATOR);

                    writeFile();
                } else {
                    throw new DavinciException("Invalid task index.");
                }
            } else {
                throw new DavinciException("Please specify the task index to unmark.");
            }
        } catch (DavinciException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task index format.");
        }
    }

    /**
     * Adds an event task to the list, handling the specified description format.
     *
     * @param taskArray Array of tasks.
     * @param description Description of the event task.
     * @return Updated array of tasks.
     * @throws DavinciException If the event format is invalid.
     */
    private static Task[] executeEventTask(Task[] taskArray, String description) throws DavinciException {
        String[] eventParts = description.split("/from", SPLIT_INTO_TWO_PARTS);
        if (eventParts.length == 2) {
            String[] eventTimeParts = eventParts[1].split("/to", SPLIT_INTO_TWO_PARTS);
            if (eventTimeParts.length == 2) {
                taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
                taskArray[taskArray.length - 1] = new Event(eventParts[0].trim(), eventTimeParts[0].trim(), eventTimeParts[1].trim());
                echoTask(taskArray);
            } else {
                throw new DavinciException("Come on man. Please use: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DavinciException("Whatcha' doing bruh, listen. Please use: event <description> /from <start> /to <end>");
        }
        return taskArray;
    }

    /**
     * Adds a deadline task to the list, handling the specified description format.
     *
     * @param taskArray Array of tasks.
     * @param description Description of the deadline task.
     * @return Updated array of tasks.
     * @throws DavinciException If the deadline format is invalid.
     */
    private static Task[] executeDeadlineTask(Task[] taskArray, String description) throws DavinciException {
        String[] deadlineParts = description.split("/by", SPLIT_INTO_TWO_PARTS);
        if (deadlineParts.length == 2) {
            taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
            taskArray[taskArray.length - 1] = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            echoTask(taskArray);
        } else {
            throw new DavinciException("Crappy formatting. Please use: deadline <description> /by <deadline>");
        }
        return taskArray;
    }

    /**
     * Adds a todo task to the list, handling the specified description format.
     *
     * @param taskArray Array of tasks.
     * @param description Description of the todo task.
     * @return Updated array of tasks.
     */
    private static Task[] executeTodoTask(Task[] taskArray, String description) {
        taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
        taskArray[taskArray.length - 1] = new Todo(description);
        echoTask(taskArray);
        return taskArray;
    }

    /**
     * Adds a task to the list in a sequential order, and handles the 3 types of commands,
     * todo, deadline, and event.
     *
     * @param userInput User input specifying the task.
     * @param taskArray Array of tasks.
     */
    private static Task[] getTasks(String userInput, Task[] taskArray) {
        try {
            Scanner taskScanner = new Scanner(userInput);
            String taskType = taskScanner.next().toLowerCase();

            if (!taskScanner.hasNext()) {
                throw new DavinciException("Come on man, specify the " + taskType + " task.");
            }

            String description = taskScanner.nextLine().trim();
            switch (taskType) {
            case "todo":
                taskArray = executeTodoTask(taskArray, description);
                break;
            case "deadline":
                taskArray = executeDeadlineTask(taskArray, description);
                break;
            case "event":
                taskArray = executeEventTask(taskArray, description);
                break;
            default:
                throw new DavinciException("Unknown task type. Please use 'todo', 'deadline', or 'event'.");
            }
            return taskArray;
        } catch (DavinciException e) {
            System.out.println("Error: " + e.getMessage());
            return taskArray;
        }
    }

    /**
     * Prints and echos back the newly added task.
     *
     * @param taskArray Array of tasks.
     */
    private static void echoTask(Task[] taskArray) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray[taskArray.length - 1].toString());
        System.out.println("Now you have " + taskArray.length + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the starting message.
     */
    private static void printStartingMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Sup! I'm DavinciBot! I was the smartest man alive, but now I am just a list maker.");
        System.out.println("Enter commands, and I will echo them back to you, as well as add them to your list.");
        System.out.println("Type 'bye' to end the conversation.");
        System.out.println("Type 'list' to see your to-do list.");
        System.out.println("Type 'mark' to mark a task as done.");
        System.out.println("Type 'unmark' to mark a task as not done.");
        System.out.println("Type 'todo <work>' to add a task to the list.");
        System.out.println("Type 'deadline <description> /by <deadline>' to add a task with a deadline to the list.");
        System.out.println("Type 'event <description> /from <start> /to <end>' to add an event to the list.");
        System.out.println("See ya bucko!");
        System.out.println(LINE_SEPARATOR);
    }
    /**
     * Reads in the user input and returns a command that corresponds to the input.
     * Warns the user if the input is invalid
     *
     * @param scanner Reads in the input.
     */
    private static void userCommand(Scanner scanner){
        while (true) {
            System.out.print("What do you want me to do? ");
            String userInput = scanner.nextLine();

            if (userInput.isEmpty()) {
                System.out.println("... are you mute?");
                continue;
            }

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye... It may be a mere few seconds for you but an eternity for me.");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTaskList(taskArray);
            } else if (userInput.toLowerCase().startsWith("mark")) {
                completeTask(userInput, taskArray);
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                unmarkTask(userInput, taskArray);
            } else if (userInput.toLowerCase().startsWith("todo") ||
                    userInput.toLowerCase().startsWith("deadline") ||
                    userInput.toLowerCase().startsWith("event")) {
                taskArray = getTasks(userInput, taskArray);
            } else {
                System.out.println("Bro, say something that I can understand.");
            }
        }
    }

    private static void startDavinici() {
        readFile();
    }

    private static void readFile() {
        try {
            List<String> lines = DavinciFileHandler.readFile(DATA_FILE_PATH);
            taskArray = new Task[0];

            for (String line : lines) {
                Task task = readLine(line);
                if (task != null && !containsTask(taskArray, task)) {
                    taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
                    taskArray[taskArray.length - 1] = task;
                    echoTask(taskArray);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (DavinciException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void writeFile() {
        try {
            List<String> lines = new ArrayList<>();
            for (Task task : taskArray) {
                lines.add(task.toFileString());
            }
            DavinciFileHandler.writeFile(DATA_FILE_PATH, lines);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private static boolean containsTask(Task[] tasks, Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    private static Task readLine(String line) throws DavinciException {
        try {
            String[] tokens = line.split("/");
            String command = tokens[0].toUpperCase();

            boolean isDone = tokens[tokens.length - 1].equals("1"); // Check if the last token is "1"

            Task newTask;

            switch (command) {
            case "TODO":
                newTask = new Todo(tokens[1]);
                break;
            case "DEADLINE":
                newTask = new Deadline(tokens[1], tokens[2]);
                break;
            case "EVENT":
                newTask = new Event(tokens[1], tokens[2], tokens[3]);
                break;
            default:
                System.out.println("Unknown task type: " + command);
                return null; // Return null for unrecognized tasks
            }

            if (isDone) {
                newTask.markAsDone();
            }

            if (!taskExists(newTask)) {
                return newTask;
            }

            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DavinciException("Corrupted file");
        }
    }

    private static boolean taskExists(Task newTask) {
        for (Task existingTask : taskArray) {
            if (existingTask.equals(newTask)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        startDavinici();
        printStartingMessage();
        userCommand(scanner);
        writeFile();

        scanner.close();
    }

}