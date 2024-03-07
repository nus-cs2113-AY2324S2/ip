package alpaca.tasks;

import java.util.List;
import java.util.ArrayList;

/**
 * Manages a list of tasks, providing operations to manipulate and query the tasks.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        System.out.println("I've removed this task: ");
        System.out.println(" " + tasks.get(taskIndex));
        this.tasks.remove(taskIndex);
    }

    /**
     * Formats the task list for saving to a file.
     *
     * @return A string representation of all tasks formatted for file storage.
     */
    public String saveTask() {
        String result = "";
        for (Task task : tasks) {
            result += task.save() + "\n";
        }
        return result;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int getTotalTaskNumber() {
        return this.tasks.size();
    }

    /**
     * Checks if a task index is valid within the current task list.
     *
     * @param taskIndex The index to check.
     * @return {@code true} if the index is valid, {@code false} otherwise.
     */
    public boolean isCountValid(int taskIndex) {
        if (taskIndex <= getTotalTaskNumber() - 1) {
            return true;
        }
        return false;
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + tasks.get(taskIndex));
    }

    /**
     * Marks the last task in the list as completed.
     */
    public void markLastTask() {
        tasks.get(tasks.size() - 1).mark();
    }

    /**
     * Unmarks a task as not completed based on its index.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + tasks.get(taskIndex));
    }

    /**
     * Lists all tasks in a formatted string.
     *
     * @return A formatted string listing all tasks with their indices.
     */
    public String listTasks() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            taskList += "     " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return taskList;
    }

    /**
     * Lists all tasks that contain the given keyword in a formatted string.
     *
     * @param keyword The keyword to filter tasks by.
     * @return A formatted string listing all matching tasks with their indices.
     */
    public String listMatchingTasks(String keyword) {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                taskList += "     " + (i + 1) + "." + tasks.get(i) + "\n";
            }
        }
        return taskList;
    }
}