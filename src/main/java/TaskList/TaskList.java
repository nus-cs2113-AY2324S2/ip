package TaskList;

import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * TaskList provides methods to add, remove, and retrieve tasks from the list.
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
     * Constructs a TaskList from the given list of tasks.
     * @param tasks The list of tasks to initialise the TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     * @param newEntry The task to add to the TaskList
     */
    public void add(Task newEntry) {
        tasks.add(newEntry);
    }
    /**
     * Retrieves the number of tasks in the TaskList.
     * @return The number of tasks in the TaskList
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Retrieves the task at a specified index.
     * @param i The index of the task to retrieve
     * @return The task at the specified index
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Deletes the task from a specified index.
     * @param index The index of the task to delete
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves all tasks in the TaskList.
     * @return The list of all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }
}
