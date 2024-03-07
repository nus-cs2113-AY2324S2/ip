import java.util.ArrayList;

/**
 * This class represents a list of tasks.
 * It provides methods to add, remove, and retrieve tasks.
 * @author Your Name
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the end of the list.
     * @param task the task to add
     */
    public void appendTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by its index.
     * @param index the index of the task to remove
     */
    public void removeTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Retrieves all tasks in the list.
     * @return an ArrayList of all tasks
     */
    public ArrayList<Task> retrieveAllTasks() {
        return tasks;
    }

    /**
     * Fetches a task from the list by its index.
     * @param index the index of the task to fetch
     * @return the task at the specified index
     */
    public Task fetchTaskByIndex(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the list of tasks.
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

