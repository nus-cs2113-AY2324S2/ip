package winter;

import winter.task.Task;

import java.util.ArrayList;

/**
 * Represents the list containing all the tasks that were added by the user.
 * Has operations to modify the list, such as <code>addNewTask</code> or <code>deleteTask</code>
 */
public class TaskList {
    private static int currentTaskIndex;
    private static ArrayList<Task> taskList;
    public TaskList() {
        currentTaskIndex = 0;
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArrayList) {
        currentTaskIndex = taskArrayList.size()-1;
        taskList = taskArrayList;
    }

    /**
     * Adds a new task to the list
     * @param task Takes a task object, which can be <code>Todo</code>, <code>Deadline</code> or <code>Event</code>
     *             due to substitutability
     */
    public void addNewTask(Task task) {
        taskList.add(task);
        currentTaskIndex++;
    }

    /**
     * Deletes a task from the task list
     * Updates the new order of the tasks left in the list after deletion
     *
     * @param taskNumber Task number of task to be deleted
     * @return The updated list after deletion
     */
    public ArrayList<Task> deleteTask(int taskNumber) {
        taskList.remove(taskNumber-1);
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setOrder(i);
        }
        currentTaskIndex--;

        return taskList;

    }

    public ArrayList<Task> getTaskArrayList() {
        return taskList;
    }

    public void markTask(int taskNumber) {
        taskList.get(taskNumber-1).mark();
    }

    public void unmarkTask(int taskNumber) {
        taskList.get(taskNumber-1).unmark();
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber-1);
    }

    public void increaseLastTaskIndex() {
        currentTaskIndex++;
    }
    public void decreaseLastTaskIndex() {
        currentTaskIndex--;
    }

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }
}
