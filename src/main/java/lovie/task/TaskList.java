package lovie.task;

import lovie.ui.Ui;
import java.util.ArrayList;

/**
 * Represents the user's list of tasks.
 */
public class TaskList {
    public static final String SPACE = " ";
    private final ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Deletes the task from the list.
     *
     * @param input The input from the user.
     */
    public void deleteTask(String input) {
        int taskNumber = Integer.parseInt(input.split(SPACE)[1]) - 1;
        if (taskNumber >= tasks.size() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            Task selectedTask = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            ui.deleteTaskPrinter(selectedTask, tasks);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param newTask The new task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Prints the tasks in the list.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            ui.emptyListPrinter();
        } else {
            ui.listPrinter(tasks);
        }
    }

    /**
     * Marks the task as done.
     *
     * @param index The index of the task.
     */
    public void unmarkTask(int index) {
        Task selectedTask = tasks.get(index);
        selectedTask.markAsUndone();
    }

    /**
     * Marks the task as undone.
     *
     * @param index The index of the task.
     */
    public void markTask(int index) {
        Task selectedTask = tasks.get(index);
        selectedTask.markAsDone();
    }

    /**
     * Finds the tasks with keyword in user task list.
     *
     * @param keyword The keyword that the user passes in.
     */
    public void find(String keyword) {
        TaskList matches = new TaskList();
        if (tasks.isEmpty()) {
            ui.emptyListPrinter();
        } else {
            for (int i = 0; i < tasks.size(); i += 1) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    matches.addTask(tasks.get(i));
                }
            }
            ui.findPrinter(matches);
        }
    }
}
