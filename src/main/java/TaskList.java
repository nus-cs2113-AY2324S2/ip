import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of tasks. It provides methods to add, remove,
 * access, and search for tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class. Initializes an empty ArrayList for tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task the Task object to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task at the specified index from the task list.
     *
     * @param index the index of the task to be retrieved
     * @return the Task object at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index the index of the task to be removed
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Searches for tasks containing the specified keyword in their description
     * (case-insensitive) and returns a list of matching tasks.
     *
     * @param keyword the keyword to search for
     * @return a list of tasks containing the keyword in their description
     */
    public List<Task> searchTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            // Implement search logic to check for keyword in task description and other relevant fields
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
