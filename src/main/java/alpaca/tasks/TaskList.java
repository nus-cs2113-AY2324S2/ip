package alpaca.tasks;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Add a task to the list  
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete a task from the list
     * @param taskIndex
     */
    public void deleteTask(int taskIndex) {
        System.out.println("I've removed this task: ");
        System.out.println(" " + tasks.get(taskIndex));
        this.tasks.remove(taskIndex);
    }

    /**
     * Save the tasks to a file
     * @return
     */
    public String saveTask() {
        String result = "";
        for (Task task : tasks) {
            result += task.save() + "\n";
        }
        return result;
    }

    /**
     * Get the total number of tasks
     * @return
     */
    public int getTotalTaskNumber() {
        return this.tasks.size();
    }

    /**
     * Get the task at the specified index
     * @param taskIndex
     * @return
     */
    public boolean isCountValid(int taskIndex) {
        if (taskIndex <= getTotalTaskNumber() - 1) {
            return true;
        }
        return false;
    }

    /**
     * Get the task at the specified index
     * @param taskIndex
     * @return
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + tasks.get(taskIndex));
    }

    /**
     * Mark the last task in the list
     */
    public void markLastTask() {
        tasks.get(tasks.size() - 1).mark();
    }

    /**
     * Unmark the last task in the list
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + tasks.get(taskIndex));
    }

    /**
     * Get the task at the specified index
     * @return
     */
    public String listTasks() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            taskList += "     " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return taskList;
    }

    /**
     * Get the task at the specified index
     * @param keyword
     * @return
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