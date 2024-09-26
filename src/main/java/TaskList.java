import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manages a list of tasks with operations to add, delete, and search tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for creating a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Constructor for creating an empty TaskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Adds a task to the list. */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task in the list.
     * @return The task that was deleted.
     * @throws AliceException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws AliceException {
        if (index < 0 || index >= tasks.size()){
            throw new AliceException("Task index out of bounds");
        }
        return tasks.remove(index);
    }

    /** Returns the list of all tasks. */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets a task by its index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Finds and returns a map of tasks matching the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A map of matching tasks with their list indexes.
     */
    public Map<Integer, Task> findTasks(String keyword) {
        Map<Integer, Task> matchingTasks = new LinkedHashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                matchingTasks.put(i + 1, tasks.get(i));
            }
        }
        return matchingTasks;
    }

    /** Returns the number of tasks in the list. */
    public int size() {
        return tasks.size();
    }
}
