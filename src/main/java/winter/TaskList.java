package winter;

import winter.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list containing all the tasks that were added by the user.
 * Has operations to modify the list, such as <code>addNewTask</code> or <code>deleteTask</code>
 */
public class TaskList {
    private static int currentTaskIndex;
    private static ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
        currentTaskIndex = 0;
    }

    public TaskList(ArrayList<Task> taskArrayList) {
        currentTaskIndex = taskArrayList.size();
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
        try {
            taskList.remove(taskNumber-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task you are trying to delete doesn't exist!");
        }
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

    public Task getTask(int taskNumber) throws IndexOutOfBoundsException {
        return taskList.get(taskNumber - 1);
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
