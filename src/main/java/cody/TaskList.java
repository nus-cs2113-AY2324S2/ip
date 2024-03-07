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
    public static final int EXPECTED_DETAILS_COUNT_TODO = 1;
    public static final int EXPECTED_DETAILS_COUNT_DEADLINE = 2;
    public static final int EXPECTED_DETAILS_COUNT_EVENT = 3;
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
                + "Now you have " + tasks.size() + " tasks in the list";
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
            throw new CodyException("Unknown command: " + input + ". Type 'help' to see the list of commands");
        }
    }

    private static Todo createTodoTask(String input) throws CodyException {
        String[] todoDetails = parseTaskInput(input, "todo", "");
        return createTodoFromDetails(todoDetails);
    }

    private static Deadline createDeadlineTask(String input) throws CodyException {
        String[] deadlineDetails = parseTaskInput(input, "deadline", " /by ");
        return createDeadlineFromDetails(deadlineDetails);
    }

    private static Event createEventTask(String input) throws CodyException {
        String[] eventDetails = parseTaskInput(input, "event", " /from | /to ");
        return createEventFromDetails(eventDetails);
    }

    private static Todo createTodoFromDetails(String[] todoDetails) {
        String description = todoDetails[0].trim();
        return new Todo(description);
    }

    private static Deadline createDeadlineFromDetails(String[] deadlineDetails) {
        String description = deadlineDetails[0].trim();
        String by = deadlineDetails[1].trim();
        return new Deadline(description, by);
    }

    private static Event createEventFromDetails(String[] eventDetails) {
        String description = eventDetails[0].trim();
        String from = eventDetails[1].trim();
        String to = eventDetails[2].trim();
        return new Event(description, from, to);
    }

    private static String[] parseTaskInput(String input, String taskType, String separator) throws CodyException {
        String taskTypePrefix = taskType + " ";
        if (!input.startsWith(taskTypePrefix)) {
            throwTaskException(taskType);
        }

        input = input.substring(taskTypePrefix.length());

        if (input.isEmpty()) {
            throwTaskException(taskType);
        }

        if (taskType.equals("todo")) {
            return new String[]{input};
        }

        String[] taskDetails = input.split(separator, getExpectedDetailsCount(taskType));
        if (taskDetails.length < getExpectedDetailsCount(taskType) || hasEmptyDetails(taskDetails)) {
            throwTaskException(taskType);
        }

        return taskDetails;
    }

    private static void throwTaskException(String taskType) throws CodyException {
        String taskFormat = getTaskFormat(taskType);
        throw new CodyException("Invalid task format for '" + taskType + "'. Please use '" + taskFormat + "'");
    }

    private static String getTaskFormat(String taskType) {
        switch (taskType) {
        case "deadline":
            return "deadline <description> /by <deadline>";
        case "event":
            return "event <description> /from <start time> /to <end time>";
        case "todo":
            return "todo <description>";
        default:
            return "";
        }
    }

    private static int getExpectedDetailsCount(String taskType) {
        switch (taskType) {
        case "deadline":
            return EXPECTED_DETAILS_COUNT_DEADLINE;
        case "event":
            return EXPECTED_DETAILS_COUNT_EVENT;
        case "todo":
            return EXPECTED_DETAILS_COUNT_TODO;
        default:
            return 0;
        }
    }

    private static boolean hasEmptyDetails(String[] details) {
        for (String detail : details) {
            if (detail.isEmpty()) {
                return true;
            }
        }
        return false;
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
                + "Now you have " + tasks.size() + " tasks in the list";
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
            listString = "There are no matching tasks in your list";
        }
        return listString;
    }
}
