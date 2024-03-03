import java.util.ArrayList;

/**
 * The TaskList class represents an ArrayList of tasks and its methods.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList using ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     * @return true if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a task to the TaskList.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList based on its index.
     * @param index Index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     * @param index Index of the task to be retrieved.
     * @return Task based on its index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
