package cody;

import cody.tasks.Deadline;
import cody.tasks.Event;
import cody.tasks.Task;
import cody.tasks.Todo;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods to manipulate and query the list.
 */
public class TaskList {
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a string representation of the list of tasks.
     *
     * @return A string representation of the list of tasks.
     */
    public String printList() {
        String listString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                    + "[" + task.getStatusIcon() + "] "
                    + task.getDescription() + "\n";
        }
        return listString;
    }

    /**
     * Marks or unmarks a task as done based on the command.
     *
     * @param command The command to mark or unmark the task.
     * @param index   The index of the task to be marked or unmarked.
     * @return A message indicating the result of the operation.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public String handleMarking(String command, int index) {
        boolean isDone = command.equals("mark");
        Task task = tasks.get(index - 1);
        task.markTask(isDone);
        return "Noted. I've marked this task as " + (isDone ? "done" : "not done") + ":\n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
    }

    /**
     * Adds a new task to the list based on the input.
     *
     * @param input The input containing the details of the task to be added.
     * @return A message indicating that the task has been added.
     * @throws CodyException If an error occurs during task creation.
     */
    public String addTask(String input) throws CodyException {
        Task task = createTaskFromInput(input);
        tasks.add(task);
        return "Got it. I've added this task:\n"
                + "[" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Creates a new task from the input.
     *
     * @param input The input containing the details of the task.
     * @return The newly created task.
     * @throws CodyException If an error occurs during task creation.
     */
    public static Task createTaskFromInput(String input) throws CodyException {
        if (input.startsWith("todo")) {
            return createTodoTask(input);
        } else if (input.startsWith("deadline")) {
            return createDeadlineTask(input);
        } else if (input.startsWith("event")) {
            return createEventTask(input);
        } else {
            throw new CodyException("Unknown command: " + input + ". Type 'help' to see the list of commands.");
        }
    }

    private static Todo createTodoTask(String input) throws CodyException {
        if (input.length() <= TODO_PREFIX_LENGTH) {
            throw new CodyException("The description of a todo cannot be empty. Please use 'todo <description>'");
        }

        String description = input.substring(TODO_PREFIX_LENGTH).trim();
        return new Todo(description);
    }

    private static Deadline createDeadlineTask(String input) throws CodyException {
        if (input.length() <= DEADLINE_PREFIX_LENGTH) {
            throw new CodyException("The description of a deadline cannot be empty. "
                    + "Please use 'deadline <description> /by <deadline>'");
        }

        String[] deadlineDetails = input.split(" /by ", 2);
        if (deadlineDetails.length < 2) {
            throw new CodyException("Invalid deadline format. Please use 'deadline <description> /by <deadline>'");
        }

        String description = deadlineDetails[0].substring(DEADLINE_PREFIX_LENGTH).trim();
        String by = deadlineDetails[1];
        return new Deadline(description, by);
    }

    private static Event createEventTask(String input) throws CodyException {
        if (input.length() <= EVENT_PREFIX_LENGTH) {
            throw new CodyException("The description of an event cannot be empty. "
                    + "Please use 'event <description> /from <start time> /to <end time>'");
        }

        String[] eventDetails = input.split(" /from | /to ", 3);
        if (eventDetails.length < 3) {
            throw new CodyException("Invalid event format. "
                    + "Please use 'event <description> /from <start time> /to <end time>'");
        }

        String description = eventDetails[0].substring(EVENT_PREFIX_LENGTH).trim();
        String from = eventDetails[1];
        String to = eventDetails[2];
        return new Event(description, from, to);
    }

    /**
     * Deletes a task from the list based on the index.
     *
     * @param index The index of the task to be deleted.
     * @return A message indicating that the task has been removed.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public String deleteTask(int index) {
        Task task = tasks.remove(index - 1);
        return "Noted. I've removed this task:\n"
                + "[" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string containing a list of tasks that match the keyword.
     */
    public String findTask(String keyword) {
        String listString = "Here are the matching tasks in your list:\n";
        String keywordLowerCase = keyword.toLowerCase();
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keywordLowerCase)) {
                listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                        + "[" + task.getStatusIcon() + "] "
                        + task.getDescription() + "\n";
                count++;
            }
        }
        if (count == 0) {
            listString = "There are no matching tasks in your list.";
        }
        return listString;
    }
}
