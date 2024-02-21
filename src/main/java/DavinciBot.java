import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;


/**
 * The DavinciBot class is a simple to-do list application that allows users
 * to manage tasks, including adding, marking as done, deleting, and listing tasks.
 */
public class DavinciBot {

    //specify the text file directory
    public static final String DATA_DIRECTORY = "./ip/data";
    public static final String DATA_FILE_PATH = DATA_DIRECTORY + "/davinci.txt";

    public static final int SPLIT_INTO_TWO_PARTS = 2;
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";
    public static final String GOODBYE = "Goodbye... It may be a mere few seconds for you but an eternity for me.";
    public static final String UNKNOWN_TASK_TYPE = "Unknown task type. Please use 'todo', 'deadline', or 'event'.";

    public static List<Task> taskList = new ArrayList<>();

    /**
     * Handles user commands and performs corresponding actions based on the input.
     */
    private static void userCommand() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = Ui.getUserInput();
            if (userInput.equalsIgnoreCase(BYE)) {
                Ui.printMessage(GOODBYE);
                break;
            }
            if (userInput.toLowerCase().startsWith(TODO) ||
                    userInput.toLowerCase().startsWith(DEADLINE) ||
                    userInput.toLowerCase().startsWith(EVENT)) {
                taskSelector(userInput);
            } else if (userInput.toLowerCase().startsWith(DELETE)) {
                try {
                    String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
                    ableToDelete(parts);
                } catch (DavinciException e) {
                    Ui.printMessage("Error: " + e.getMessage());
                } catch (NumberFormatException e) {
                    Ui.printMessage("Error: Invalid task index format.");
                }
            } else {
                Parser.parseUserInput(userInput, taskList);
            }
        }
        scanner.close();
    }

    /**
     * Selects the appropriate task type based on user input.
     *
     * @param userInput User input containing the task type and details.
     */
    private static void taskSelector(String userInput) {
        try {
            Scanner taskScanner = new Scanner(userInput);
            String taskType = taskScanner.next().toLowerCase();
            if (!taskScanner.hasNext()) {
                throw new DavinciException("Come on man, specify the " + taskType + " task.");
            }
            String description = taskScanner.nextLine().trim();
            switch (taskType) {
            case TODO:
                executeTodoTask(description);
                break;
            case DEADLINE:
                executeDeadlineTask(description);
                break;
            case EVENT:
                executeEventTask(description);
                break;
            default:
                throw new DavinciException(UNKNOWN_TASK_TYPE);
            }
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        }
    }

    /**
     * Adds an event task to the task list based on user input.
     *
     * @param description Description of the event task with start and end times.
     * @throws DavinciException If there is an issue with the event task format.
     */
    private static void executeEventTask(String description) throws DavinciException {
        String[] eventParts = description.split("/from", SPLIT_INTO_TWO_PARTS);
        if (eventParts.length == 2) {
            String[] eventTimeParts = eventParts[1].split("/to", SPLIT_INTO_TWO_PARTS);
            if (eventTimeParts.length == 2) {
                taskList.add(new Event(eventParts[0].trim(), eventTimeParts[0].trim(), eventTimeParts[1].trim()));
                Ui.echoTask(taskList);
            } else {
                throw new DavinciException("Come on man. Please use: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DavinciException("Whatcha' doing bruh, listen. " +
                    "Please use: event <description> /from <start> /to <end>");
        }
        taskList.toArray(new Task[0]);
    }

    /**
     * Adds a deadline task to the task list based on user input.
     *
     * @param description Description of the deadline task with the deadline.
     * @throws DavinciException If there is an issue with the deadline task format.
     */
    private static void executeDeadlineTask(String description) throws DavinciException {
        String[] deadlineParts = description.split("/by", SPLIT_INTO_TWO_PARTS);
        if (deadlineParts.length == 2) {
            taskList.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            Ui.echoTask(taskList);
        } else {
            throw new DavinciException("Crappy formatting. Please use: deadline <description> /by <deadline>");
        }
        taskList.toArray(new Task[0]);
    }

    /**
     * Adds a todo task to the task list based on user input.
     *
     * @param description Description of the todo task.
     */
    private static void executeTodoTask(String description) {
        taskList.add(new Todo(description));
        Ui.echoTask(taskList);
        taskList.toArray(new Task[0]);
    }

    /**
     * Deletes a task based on the user input.
     *
     * @param parts User input containing the command and task index.
     * @throws DavinciException If there is an issue with deleting the task.
     */
    private static void ableToDelete(String[] parts) throws DavinciException {
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.remove(taskIndex);
                Ui.printMessage("Noted. I've removed this task:");
                Ui.displayTaskList(taskList);
                writeFile();
            } else {
                throw new DavinciException("Invalid task index.");
            }
        } else {
            throw new DavinciException("Please specify the task index to delete.");
        }
    }

    /**
     * Writes the current task list to a file.
     */
    public static void writeFile() {
        try {
            createDataDirectory();
            List<String> lines = new ArrayList<>();
            for (Task task : taskList) {
                lines.add(task.toFileString());
            }
            DavinciFileHandler.writeFile(DATA_FILE_PATH, lines);
        } catch (IOException e) {
            Ui.printMessage("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Checks if a task already exists in the task list.
     *
     * @param tasks The list of tasks to check.
     * @param task  The task to check for existence.
     * @return True if the task exists, false otherwise.
     */
    private static boolean containsTask(List<Task> tasks, Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads tasks from a file and adds them to the task list.
     */
    private static void readFile() {
        try {
            createDataDirectory();
            List<String> lines = DavinciFileHandler.readFile(DATA_FILE_PATH);
            taskList.clear();

            for (String line : lines) {
                Task task = readLine(line);
                if (task != null && !containsTask(taskList, task)) {
                    taskList.add(task);
                    Ui.printMessage("Got it. I've added a task from the file:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    Ui.displayTaskList(taskList);
                }
            }
        } catch (IOException e) {
            Ui.printMessage("Error reading file: " + e.getMessage());
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        }
    }
    private static void createDataDirectory() {
        try {
            DavinciFileHandler.createDirectories(Paths.get(DATA_DIRECTORY));
        } catch (IOException e) {
            Ui.printMessage("Error creating data directory: " + e.getMessage());
        }
    }

    /**
     * Reads a line from the file and converts it into a Task object.
     *
     * @param line The line read from the file.
     * @return A Task object representing the line.
     * @throws DavinciException If there is an issue with the file format.
     */
    private static Task readLine(String line) throws DavinciException {
        try {
            String[] tokens = line.split("/");
            String command = tokens[0].toLowerCase();
            boolean isDone = tokens[tokens.length - 1].equals("1");
            Task newTask = commandCases(command, tokens);
            if (newTask == null) {
                return null;
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

    /**
     * Processes the command and creates a corresponding Task object.
     *
     * @param command The command representing the type of task.
     * @param tokens  An array of tokens from the line.
     * @return A Task object based on the command.
     */
    private static Task commandCases(String command, String[] tokens) {
        Task newTask;
        switch (command) {
        case TODO:
            newTask = new Todo(tokens[1]);
            break;
        case DEADLINE:
            newTask = new Deadline(tokens[1], tokens[2]);
            break;
        case EVENT:
            newTask = new Event(tokens[1], tokens[2], tokens[3]);
            break;
        default:
            System.out.println("Unknown task type: " + command);
            return null;
        }
        return newTask;
    }

    /**
     * Checks if a task already exists in the task list.
     *
     * @param newTask The task to check for existence.
     * @return True if the task exists, false otherwise.
     */
    private static boolean taskExists(Task newTask) {
        for (Task existingTask : taskList) {
            if (existingTask.equals(newTask)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        readFile();
        Ui.printStartingMessage();
        userCommand();
        writeFile();
    }
}
