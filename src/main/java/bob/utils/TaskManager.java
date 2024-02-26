package bob.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * Handles the storage and management of Tasks. Task operations (add, delete, list) are done by the TaskManager.
 */
public class TaskManager {
    private final List<Task> tasks;
    private int taskCount;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Tokenize Tasks. Task properties/variables are set as individual elements of the inner List.
     *
     * @return List of Lists of String. Each inner List represents a tokenized Task.
     */
    public List<List<String>> tokenizeTasks() {
        List<List<String>> tokenizedTaskList = new ArrayList<>();

        for (Task task : tasks) {
            List<String> token = new ArrayList<>();
            token.add("T");
            token.add(task.getCompletionStatus());
            token.add(task.getTaskName());

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                token.set(0, "D");
                token.add(deadline.getDueDate());
            } else if (task instanceof Event) {
                Event event = (Event) task;
                token.set(0, "E");
                token.add(event.getStartDate());
                token.add(event.getEndDate());
            }

            tokenizedTaskList.add(token);
        }

        return tokenizedTaskList;
    }

    /**
     * Add a new Task to the Task List.
     *
     * @param newTask New Task to be added.
     * @return Result String from the operation performed.
     */
    private String addNewTask(Task newTask) {
        String output = " Got it. I've added this task:\n";

        tasks.add(newTask);
        taskCount++;

        output += String.format("   %s\n", newTask);
        output += String.format(" Now you have %d tasks in the list.", taskCount);

        return output;
    }

    /**
     * Add a new Todo Task.
     *
     * @param taskName Name of Todo Task to be added.
     * @return Result String from the addition operation.
     */
    public String addTodo(String taskName) {
        Task newTodo = new Todo(taskName);
        return addNewTask(newTodo);
    }

    /**
     * Add a new Deadline Task.
     *
     * @param taskName Name of Deadline Task to be added.
     * @param dueDate Due date of the Deadline Task.
     * @return Result String from the addition operation.
     */
    public String addDeadline(String taskName, LocalDateTime dueDate) {
        Task newDeadline = new Deadline(taskName, dueDate);
        return addNewTask(newDeadline);
    }

    /**
     * Add a new Event Task.
     *
     * @param taskName Name of Event Task to be added.
     * @param startDate Start date of the Event Task.
     * @param endDate End date of the Event Task.
     * @return Result String from the addition operation.
     */
    public String addEvent(String taskName, LocalDateTime startDate, LocalDateTime endDate) {
        Task newEvent = new Event(taskName, startDate, endDate);
        return addNewTask(newEvent);
    }

    /**
     * List out all Tasks in the Task List.
     *
     * @return String representation of all Tasks in the Task List.
     */
    public String listTasks() {
        StringBuilder output = new StringBuilder(" Here are the tasks in your list:\n");

        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks.get(i);
            int currentTaskId = i + 1;
            output.append(String.format(" %d.%s\n", currentTaskId, currentTask));
        }

        return output.toString().stripTrailing();
    }

    /**
     * Update the progress of a Task in the Task List.
     *
     * @param taskId ID of the Task to be updated.
     * @param command The operation to be performed on the Task (mark/unmark).
     * @return Result String from the update operation.
     */
    public String updateTaskProgress(int taskId, String command) {
        String output;
        int currentTaskIndex = taskId - 1; // Index in array is ID - 1
        Task currentTask = tasks.get(currentTaskIndex);

        if (command.equals("MARK")) {
            output = " Nice! I've marked this task as done:\n";
            currentTask = currentTask.markTaskAsComplete();
        } else {
            output = " OK, I've marked this task as not done yet:\n";
            currentTask = currentTask.markTaskAsIncomplete();
        }

        output += String.format("   %s", currentTask);

        tasks.set(currentTaskIndex, currentTask); // Update array

        return output;
    }

    /**
     * Deletes a Task from the Task List.
     *
     * @param taskId ID of the Task to be deleted.
     * @return Result String from the deletion operation.
     */
    public String deleteTask(int taskId) {
        int currentTaskIndex = taskId - 1;
        Task taskToDelete = tasks.remove(currentTaskIndex);
        taskCount--;

        String output = " Noted. I've removed this task:\n";
        output += String.format("   %s\n", taskToDelete);
        output += String.format(" Now you have %d tasks in the list.", taskCount);

        return output;
    }

    /**
     * Finds a Task in the Task List.
     *
     * @param keyword Keyword to search for.
     * @return String representation of all Tasks matching the keyword provided.
     */
    public String findTask(String keyword) {
        StringBuilder output = new StringBuilder(" Here are the matching tasks in your list:\n");

        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks.get(i);
            int currentTaskId = i + 1;
            String currentTaskName = currentTask.getTaskName();
            if (currentTaskName.contains(keyword)) {
                output.append(String.format(" %d.%s\n", currentTaskId, currentTask));
            }
        }

        return output.toString().stripTrailing();
    }
}
