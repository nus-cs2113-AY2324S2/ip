import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks. The class provided the functionality to add, remove, mark and find tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList using the tasks provided. If the provided list is null, it initializes
     * an empty task list.
     * @param loadedTasks The list of tasks to intialize the TaskList with.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks != null ? loadedTasks : new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as done or not done based on the given index.
     * @param index The index of the task in the task list.
     * @param isDone The new done status of the task.
     * @throws DukeException If the provided index is out of bounds.
     */
    public void markTask(int index, boolean isDone) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number is out of bounds.");
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    /**
     * Deletes a task from the list based on the given index.
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws DukeException If the provided index is out of bounds.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number is out of bounds.");
        }
        return tasks.remove(index);
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword in their description.
     * @param keyword The keyword to filter the tasks by.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
