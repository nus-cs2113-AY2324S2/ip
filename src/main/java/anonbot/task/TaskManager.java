package anonbot.task;

import java.util.ArrayList;

import anonbot.exception.EmptyTaskArgumentException;
import anonbot.exception.InvalidArgumentException;
import anonbot.exception.InvalidMarkArgumentException;

/**
 * Provides functionality to support the manipulation of tasks.
 * This includes creating, updating and deleting tasks.
 */
public class TaskManager {
    /**
     * Number of tasks created throughout (both currently active and deleted)
     */
    private static int totalTasksCreated = 0;
    private static final ArrayList<Task> taskList = new ArrayList<Task>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void setTotalTasksCreated(int totalTasks) {
        totalTasksCreated = totalTasks;
    }

    public static int getNumberOfActiveTasks() {
        return totalTasksCreated;
    }

    /**
     * Creates a new or existing task.
     * The taskNumber serves as a unique task ID.
     * If creating a new task, the taskNumber will be based on the total tasks created to date
     * If reading from a save file, the taskNumber will be based on the save file.
     *
     * @param taskDescription The description of the task, including any optional parameters.
     * @param taskType The type of task.
     * @param taskNumber The unique task ID for the associated task.
     * @param isTaskDone Whether the task should be marked as done or unudone.
     * @return The newly created task.
     */
    public static Task createTask(String taskDescription, Task.TaskType taskType,
            int taskNumber, boolean isTaskDone) {
        Task newTask;

        switch (taskType) {
        case TODO:
            newTask = new Todo(taskDescription, taskNumber);
            break;
        case DEADLINE:
            newTask = new Deadline(taskDescription, taskNumber);
            break;
        case EVENT:
            newTask = new Event(taskDescription, taskNumber);
            break;
        default:
            newTask = null;
        }
        if (newTask != null) {
            newTask.setTaskStatus(isTaskDone);
            taskList.add(newTask);
        }
        return newTask;
    }

    /**
     * Creates a brand-new task.
     * A task will be created as long as there is a task description.
     * Does not check the validity of the format (`/by`, `/to`, `/from`) for the deadline and event tasks.
     * The task number will be automatically provided based on how many tasks have been created to date.
     *
     * @param taskDescription The description of the task, including any optional parameters.
     * @param taskType the type of task.
     * @throws EmptyTaskArgumentException If the associated task has no task description when one is expected.
     */
    public static void createNewTask(String taskDescription, Task.TaskType taskType)
            throws EmptyTaskArgumentException {
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskArgumentException(taskType.name().toLowerCase());
        }
        if (taskType == Task.TaskType.INVALID) {
            System.out.println("Invalid Task Type");
            return;
        }

        totalTasksCreated += 1;
        Task newTask = createTask(taskDescription, taskType, totalTasksCreated, false);
        if (newTask != null) {
            System.out.println("Alright. I have added this task: ");
            newTask.printTask();
        } else {
            System.out.println("Oops, Something went wrong. I can't add the task requested.");
        }
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints out the whole list of tasks that have been added so far.
     */
    public static void printTaskList() {
        System.out.println("Here are the tasks at hand:");
        for (Task t : taskList) {
            t.printTask();
        }
    }

    /**
     * Finds and list tasks that matches the keyword or keyphrase.
     *
     * @param keyphrase A non-empty keyword or keyphrase to search through the task list.
     */
    public static void printTasksUsingKeyphrase(String keyphrase) {
        System.out.println("Here are the available tasks found using the keyword:");
        for (Task t : taskList) {
            String taskDescription = t.getTaskDescription();
            if (taskDescription.toLowerCase().contains(keyphrase.toLowerCase())) {
                t.printTask();
            }
        }
    }

    /**
     * Retrieves the task associated by its task number.
     *
     * @param taskNumber The task number to retrieve.
     * @return The task associated with the task number. `null` if the task is not found.
     */
    private static Task retrieveTask(int taskNumber) {
        for (Task task : taskList) {
            if (task.getTaskNumber() == taskNumber) {
                return task;
            }
        }
        return null;
    }

    /**
     * Marks the corresponding task as done.
     *
     * @param taskNumber The valid task ID of the corresponding task to mark.
     * @throws InvalidMarkArgumentException If the specified task ID is not found in currently active tasks.
     */
    public static void markTaskAsDone(int taskNumber) throws InvalidMarkArgumentException {
        Task taskToMark = retrieveTask(taskNumber);

        if (taskToMark == null) {
            throw new InvalidMarkArgumentException("mark", Integer.toString(taskNumber));
        }

        taskToMark.markAsDone();
    }

    /**
     * Marks the corresponding task as undone.
     *
     * @param taskNumber The valid task ID of the corresponding task to unmark.
     * @throws InvalidMarkArgumentException If the specified task ID is not found in currently active tasks.
     */
    public static void markTaskAsUndone(int taskNumber) throws InvalidMarkArgumentException {
        Task taskToMark = retrieveTask(taskNumber);

        if (taskToMark == null) {
            throw new InvalidMarkArgumentException("unmark", Integer.toString(taskNumber));
        }

        taskToMark.markAsUndone();
    }

    /**
     * Deletes the corresponding task.
     * Once deleted, the task cannot be recovered.
     *
     * @param taskNumber The valid task ID of the corresponding task to delete.
     * @throws InvalidArgumentException If the specified task ID is not found in currently active tasks.
     */
    public static void deleteTask(int taskNumber) throws InvalidArgumentException {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskNumber() == taskNumber) {
                System.out.println("Ok. Task Removed: ");
                task.printTask();
                taskList.remove(i);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                return;
            }
        }
        throw new InvalidArgumentException("delete", Integer.toString(taskNumber));
    }
}
