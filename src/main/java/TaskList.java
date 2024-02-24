import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a collection of tasks and provides methods to manipulate the task list.
 */
public class TaskList {

    public static final String DATA_DIRECTORY = "./ip/data";
    public static final String DATA_FILE_PATH = DATA_DIRECTORY + "/davinci.txt";
    public static final int SPLIT_INTO_TWO_PARTS = 2;
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String UNKNOWN_TASK_TYPE = "Unknown task type. Please use 'todo', 'deadline', or 'event'.";
    public static List<Task> taskList = new ArrayList<>();

    /**
     * Adds a task to the task list and writes the updated list to the file.
     *
     * @param task The task to be added.
     */
    public static void addTask(Task task) {
        taskList.add(task);
        writeFile();
    }

    /**
     * Deletes a task from the task list based on the specified index.
     * Throws an exception if the index is invalid.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws DavinciException If the task index is invalid.
     */
    public static void deleteTask(int taskIndex) throws DavinciException {
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task deletedTask = taskList.get(taskIndex);
            taskList.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            Ui.displaySingleTask(deletedTask);
            Ui.displayTaskList(taskList);
            writeFile();
        } else {
            throw new DavinciException("Invalid task index.");
        }
    }

    /**
     * Gets the current tasklist.
     *
     * @return The list of tasks.
     */
    public static List<Task> getTaskList() {
        return taskList;
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
     * Reads tasks from a file and adds them to the tasklist.
     */
    public static void readFile() {
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

    /**
     * Creates the data directory if it does not exist.
     */
    private static void createDataDirectory() {
        try {
            DavinciFileHandler.createDirectories(Paths.get(DATA_DIRECTORY));
        } catch (IOException e) {
            Ui.printMessage("Error creating data directory: " + e.getMessage());
        }
    }

    /**
     * Reads a line from the file and converts it to a Task object.
     *
     * @param line The line read from the file.
     * @return The Task object created from the line.
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
     * Checks if a task already exists in the tasklist.
     *
     * @param newTask The task to check for existence.
     * @return True if the task already exists, false otherwise.
     */
    private static boolean taskExists(Task newTask) {
        for (Task existingTask : taskList) {
            if (existingTask.equals(newTask)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a Task object based on the command and tokens.
     *
     * @param command The command specifying the type of task.
     * @param tokens  The tokens representing task details.
     * @return The Task object created based on the command and tokens.
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
     * Checks if a task already exists in the provided list of tasks.
     *
     * @param tasks The list of tasks to check.
     * @param task  The task to check for existence.
     * @return True if the task already exists, false otherwise.
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
     * Handles user input by selecting the appropriate task type.
     *
     * @param userInput The user input containing the task type and details.
     */
    public static void handleUserInput(String userInput) {
        taskSelector(userInput);
    }

    /**
     * Selects the appropriate task type based on user input.
     *
     * @param userInput User input containing the task type and details.
     */
    public static void taskSelector(String userInput) {
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
                addTask(new Event(eventParts[0].trim(), eventTimeParts[0].trim(), eventTimeParts[1].trim()));
                Ui.echoTask(getTaskList());
            } else {
                throw new DavinciException("Come on man. Please use: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DavinciException("Whatcha' doing bruh, listen. " +
                    "Please use: event <description> /from <start> /to <end>");
        }
        writeFile();
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
            addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            Ui.echoTask(getTaskList());
        } else {
            throw new DavinciException("Crappy formatting. Please use: deadline <description> /by <deadline>");
        }
        writeFile();
    }

    /**
     * Adds a todo task to the task list based on user input.
     *
     * @param description Description of the todo task.
     */
    private static void executeTodoTask(String description) {
        addTask(new Todo(description));
        Ui.echoTask(getTaskList());
        writeFile();
    }

    /**
     * Deletes a task based on the user input.
     *
     * @param parts User input containing the command and task index.
     * @throws DavinciException If there is an issue with deleting the task.
     */
    public static void ableToDelete(String[] parts) throws DavinciException {
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < TaskList.getTaskList().size()) {
                deleteTask(taskIndex);
            } else {
                throw new DavinciException("Invalid task index.");
            }
        } else {
            throw new DavinciException("Please specify the task index to delete.");
        }
    }
}