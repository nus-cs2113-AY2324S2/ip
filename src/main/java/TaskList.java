import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    public static ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        indicateNewTask(tasks.get(tasks.size() - 1), tasks.size());
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param indexTask The index of the task to be deleted.
     */
    public void deleteTask(int indexTask) {
        Task removedTask = tasks.get(indexTask - 1);
        tasks.remove(removedTask);
        Ui.deleteTaskDisplay(indexTask, removedTask);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param indexTask The index of the task to be marked as completed.
     */

    public void markTaskAsCompleted(int indexTask) {
        tasks.get(indexTask - 1).markAsCompleted();
        Ui.markedTasksAsCompletedDisplay(indexTask);
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTasks() {
        Ui.listTasksDisplay();
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param indexTask The index of the task to be marked as not completed.
     */
    public void markTaskAsNotCompleted(int indexTask) {
        tasks.get(indexTask - 1).markAsNotCompleted();
        Ui.markedTasksAsNotCompletedDisplay(indexTask);
    }

    /**
     * Indicates the addition of a new task.
     *
     * @param newTask              The new task added.
     * @param currentNumberOfTasks The current number of tasks after addition.
     */
    public void indicateNewTask(Task newTask, int currentNumberOfTasks) {
        Ui.indicateNewTaskDisplay(newTask, currentNumberOfTasks);
    }

    /**
     * Finds tasks containing the specified keyword.
     *
     * @param keyword   The keyword to search for.
     * @param tasksList The list of tasks to search within.
     */
    public void findTasks(String keyword, ArrayList<Task> tasksList) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            Ui.noTasksFoundDisplay();
        } else {
            Ui.showTasksFoundDisplay(matchingTasks);
        }
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }
}