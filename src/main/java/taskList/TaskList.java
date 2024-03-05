package taskList;

import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Manage a list of tasks.
 * This class is responsible for adding, deleting, marking tasks as done or not done,
 * and searching tasks by keyword.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with a pre-existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        }
    }

    /**
     * Marks a task as not done based on its index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
        }
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return An ArrayList containing the current tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if the TaskList is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Formats the tasks in the TaskList into a string representation.
     *
     * @return A string representation of all tasks in the TaskList.
     */
    public String getFormattedTasks() {
        if(tasks.isEmpty()) {
            return "OPPS! Your task list is empty :<";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
            }
            return sb.toString().trim();
        }
    }

    /**
     * Finds and returns tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for(Task task: tasks) {
            if(task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
