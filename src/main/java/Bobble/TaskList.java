package Bobble;

import Bobble.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class contains the task list and provides methods for managing them.
 */
public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The task to be added.
     */
    public void addNewTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The index of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index of the task to be marked as done.
     */
    public void markTask(int taskNumber) {
        taskList.get(taskNumber).setDone(true);
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNumber The index of the task to be marked as not done.
     */
    public void unmarkTask(int taskNumber) {
        taskList.get(taskNumber).setDone(false);
    }

    /**
     * Finds tasks containing a specified keyword in their description and displays them.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        Task task;

        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println(Ui.LINE_WRAP + "No matching tasks found.\n" + Ui.LINE_WRAP);
        } else {
            System.out.println(Ui.LINE_WRAP + "Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("  " + (i + 1) + "." + matchingTasks.get(i));
            }
            System.out.println(Ui.LINE_WRAP);
        }
    }
}

