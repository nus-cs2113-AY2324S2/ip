import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * The TaskList class manages the list of tasks and provides methods to handle various tasks.
 */
public class TaskList {
    /** The list of tasks. */
    public static ArrayList<Task> list = new ArrayList<>();
    /** The storage object to interact with file storage. */
    private final Storage storage;
    /** Indicates whether the task list is active. */
    static Boolean isActive = true;

    /**
     * Constructs a TaskList object with the specified storage.
     *
     * @param storage The storage object to interact with file storage.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Sets the activity status of the task list.
     *
     * @param value The value to set for the activity status.
     */
    public static void setActive(Boolean value) {
        isActive = value;
    }

    /**
     * Loads tasks from storage.
     *
     * @throws FileNotFoundException    If the tasks file is not found.
     * @throws InvalidInputException    If the input format in the file is invalid.
     */
    public void loadTasks() throws FileNotFoundException, InvalidInputException {
        storage.loadTasks();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Handles the "bye" command.
     */
    static void handleByeTask() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Handles the "list" command.
     */
    static void handleListTask() {
        if (list.isEmpty()) {
            System.out.println("Your to-do list is empty.");
        } else {
            System.out.println("List so far: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }
        }
    }

    /**
     * Handles the "delete" command.
     *
     * @param line The command line.
     * @throws InvalidInputException If the task number is invalid.
     */
    static void handleDeleteTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                System.out.println("Deleting task: " + list.get(taskNumber));
                list.remove(taskNumber);
                System.out.println("Task deleted successfully!");
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        }
    }

    /**
     * Handles the "mark" command.
     *
     * @param line The command line.
     * @throws InvalidInputException If the task number is invalid or the input is incorrect.
     */
    static void handleMarkTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list.get(taskNumber).setDone(true);
                System.out.println("I have marked this task as done: " + list.get(taskNumber));
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    /**
     * Handles the "unmark" command.
     *
     * @param line The command line.
     * @throws InvalidInputException If the task number is invalid or the input is incorrect.
     */
    static void handleUnmarkTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list.get(taskNumber).setDone(false);
                System.out.println("I have marked this task as not done: " + list.get(taskNumber));
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    /**
     * Handles the "deadline" command.
     *
     * @param line The command line.
     * @throws InvalidInputException If the input format is incorrect.
     */
    static void handleDeadlineTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int byIndex = remaining.indexOf("/by");
        if (byIndex == -1 || byIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'deadline <description> /by <time>'");
        }
        final int DESCRIPTION_END_INDEX = byIndex;
        final int BY_START_INDEX = byIndex + 3; // "/by".length() == 3
        String description = remaining.substring(0, DESCRIPTION_END_INDEX).trim();
        String by = remaining.substring(BY_START_INDEX).trim();
        list.add(new Deadline(description, by, false));
        System.out.println("Added: " + list.get(list.size() - 1) + "\nNow you have " + list.size() + " items in the list!");
    }

    /**
     * Handles the "event" command.
     *
     * @param line The command line.
     * @throws InvalidInputException If the input format is incorrect.
     */
    static void handleEventTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int fromIndex = remaining.indexOf("/from");
        if (fromIndex == -1 || fromIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        final int DESCRIPTION_END_INDEX = fromIndex;
        final int FROM_START_INDEX = fromIndex + 5; // "/from".length() == 5
        int toIndex = remaining.indexOf("/to");
        if (toIndex == -1 || toIndex <= FROM_START_INDEX) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        final int TO_START_INDEX = toIndex + 3; // "/to".length() == 3
        String description = remaining.substring(0, DESCRIPTION_END_INDEX).trim();
        String from = remaining.substring(FROM_START_INDEX, toIndex).trim();
        String to = remaining.substring(TO_START_INDEX).trim();
        list.add(new Events(description, from, to, false));
        System.out.println("Added: " + list.get(list.size() - 1) + "\nNow you have " + list.size() + " items in the list!");
    }

    /**
     * Handles the "todo" command.
     *
     * @param line The command line.
     */
    static void handleTodoTask(String line) {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        list.add(new ToDo(remaining, false));
        System.out.println("Added: " + list.get(list.size() - 1) + "\nNow you have " + list.size() + " items in the list!");
    }

    /**
     * Handles the "find" command.
     *
     * @param line The command line.
     */
    static void handleFindTask(String line) {
        String query = line.substring("find".length()).trim();
        ArrayList<Task> matchingTasks = findTasks(query);
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Finds tasks matching the given query.
     *
     * @param query The search query.
     * @return The list of matching tasks.
     */
    private static ArrayList<Task> findTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(query)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Checks if the task number is valid.
     *
     * @param taskNumber The task number to check.
     * @return True if the task number is valid, otherwise false.
     */
    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < list.size();
    }
}
