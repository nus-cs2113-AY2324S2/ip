package quill.task;

import java.util.ArrayList;

/**
 * The TaskList Class contains the task list and provides
 * methods to manage and retrieve tasks from the task list.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public static int getTotalTasks() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a task from the task list.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (tasks.isEmpty());
    }

    /**
     * Retrieves a specific task from the task list by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

}
