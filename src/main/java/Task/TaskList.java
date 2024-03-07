package task;

import ui.Ui;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList{

    private final List<Task> tasks;
    private final Ui ui;

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui("aoliba");
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.showLine();
        ui.showTaskAdded(task.getDescription());
        ui.showSize(tasks.size());
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The index of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.invalidIndex();
            return;
        }
        System.out.println("Noted. I've removed this task: " + tasks.get(taskNumber-1).getDescription());
        tasks.remove(taskNumber - 1);
    }

    /**
     * Displays all tasks in the task list.
     */
    public void listTasks() {
        ui.showLine();
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        ui.showSize(tasks.size());
        ui.showLine();
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskNumber The index of the task to be marked.
     * @param markDone   A boolean value indicating whether to mark the task as done or not done.
     */
    public void markTaskAsDone(int taskNumber, boolean markDone) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.invalidIndex();
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        if (!task.isDone() && markDone) {
            task.markAsDone();
        } else if (task.isDone() && !markDone) {
            task.markAsNotDone();
        } else {
            System.out.println("Task already marked as " + (markDone ? "done": "not done yet"));
            return;
        }
        System.out.println("Nice! I've marked this task as " + (markDone ? "done": "not done yet"));
    }

    /**
     * Retrieves the tasks in the task list.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that match the given keywords.
     *
     * @param keywords The keywords to search for.
     */
    public void findTasks(String keywords) {
        int matches = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(keywords)) {
                if (matches == 0) {
                    ui.showLine();
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println((matches + 1) + "." + task);
                matches++;
            }
        }
        if (matches == 0) {
            System.out.println("No matching tasks found.");
        }
        ui.showLine();
    }
}