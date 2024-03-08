package lovie.task;

import lovie.ui.Ui;
import java.util.ArrayList;

/**
 * Represents the user's list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasksList;
    private final Ui ui;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        tasksList = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Deletes the task from the list.
     *
     * @param input The input from the user.
     */
    public void deleteTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= tasksList.size() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            Task selectedTask = tasksList.get(taskNumber);
            tasksList.remove(taskNumber);
            ui.deleteTaskPrinter(selectedTask, tasksList);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param newTask The new task to be added.
     */
    public void addTask(Task newTask) {
        tasksList.add(newTask);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return tasksList.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasksList.get(index);
    }

    /**
     * Prints the tasks in the list.
     */
    public void printTasks() {
        if (tasksList.isEmpty()) {
            ui.emptyListPrinter();
        } else {
            ui.listPrinter(tasksList);
        }
    }

    /**
     * Marks the task as done.
     *
     * @param index The index of the task.
     */
    public void unmarkTask(int index) {
        Task selectedTask = tasksList.get(index);
        selectedTask.markAsUndone();
    }

    /**
     * Marks the task as undone.
     *
     * @param index The index of the task.
     */
    public void markTask(int index) {
        Task selectedTask = tasksList.get(index);
        selectedTask.markAsDone();
    }

    public void find(String keyword) {
        TaskList matchedList = new TaskList();
        if (tasksList.isEmpty()) {
            ui.emptyListPrinter();
        } else {
            for (int i = 0; i < tasksList.size(); i += 1) {
                if (tasksList.get(i).getDescription().contains(keyword)) {
                    matchedList.addTask(tasksList.get(i));
                }
            }
            ui.findPrinter(matchedList); 
        }
    }
}
