import java.util.ArrayList;

/**
 * TaskList class represents a list of tasks for the Duke application.
 */
public class TaskList {

    /** ArrayList to store tasks. */
    private ArrayList<Task> tasks;

    /**
     * Default constructor for TaskList class, initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class with an existing ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the ArrayList of tasks in the task list.
     *
     * @return ArrayList of tasks in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes a task from the task list based on the specified index.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
}
