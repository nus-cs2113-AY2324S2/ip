package Event;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>TaskList</code> class manages a list of tasks in the application.
 * This class provides functionality to add, remove, and query tasks. It also
 * allows marking tasks as done or not done.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList. Initializes the internal list to an empty
     * ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a preloaded list of tasks.
     * Used for initializing the TaskList with tasks loaded from storage.
     *
     * @param loadedTasks An ArrayList of Task objects to be added to the TaskList
     * upon initialization.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task object to be outputted.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the internal list of tasks.
     * Used for operations that require direct access to the full list
     * of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markTaskAsNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Searches and returns a list of tasks that contain the specified word
     * in their description.
     *
     * @param word The word to search for in the task descriptions.
     * @return A List of Task objects that contain the specified word in
     * their description.
     */
    public List<Task> findTasks(String word) {
        List<Task> matchingTask = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(word)) {
                matchingTask.add(task);
            }
        }
        return matchingTask;
    }
}
