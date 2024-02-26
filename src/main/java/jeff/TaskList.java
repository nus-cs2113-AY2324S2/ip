package jeff;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the application.
 * Provides methods for adding, removing, and manipulating tasks within the list.
 * Serves as a container for managing tasks in the application.
 */
public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public static void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public static boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves the task at the specified index from the list.
     *
     * @param index Index of the task to retrieve.
     * @return Task at the specified index.
     */
    public static Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the last task in task list.
     *
     * @return Last task in the list.
     */
    public static Task back() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Removes the task at the specified index from the list.
     *
     * @param index Index of the task to remove.
     * @return The removed task.
     */
    public static Task remove(int index) {
        return tasks.remove(index);
    }
}
