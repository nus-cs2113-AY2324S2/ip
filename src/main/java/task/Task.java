package task;

import java.util.ArrayList;

public class Task {
    private static final int MAX_TASKS = 128;
    private static int totalTasks = 0;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    private String taskDescription;
    private int taskNumber;
    private boolean isTaskDone;

    /**
     * Creates a new Task.
     * An associated task number is assigned to the task.
     * The created Task is also added to the taskList (at the end) for tracking.
     *
     * @param taskDescription Description of the task.
     */
    public Task(String taskDescription) {
        setTaskDescription(taskDescription);
        setTaskNumber();
        setTaskStatus(false);
        totalTasks++;
        taskList.add(this);
    }

    /**
     * Gets the task description.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Sets the task description.
     *
     * @param taskDescription Description of the task.
     */
    private void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Give the newly created task an associated task number.
     */
    private void setTaskNumber() {
        this.taskNumber = totalTasks + 1;
    }

    /**
     * Sets the status of the associated task.
     *
     * @param isTaskDone Whether the task has been marked completed.
     */
    private void setTaskStatus(boolean isTaskDone) {
        this.isTaskDone = isTaskDone;
    }

    /**
     * Prints out the whole list of tasks that have been added so far.
     */
    public static void printTaskList() {
        System.out.println("Here are the tasks at hand:");
        for (Task t : taskList) {
            System.out.format("%d.[%c] %s\n", t.taskNumber, t.isTaskDone ? 'X' : ' ', t.taskDescription);
        }
    }

    /**
     * Retrieves the task associated by its task number.
     *
     * @param taskNumber The task number to retrieve.
     * @return The task associated with the task number. `null` if the task is not found.
     */
    public static Task getTask(int taskNumber) {
        for (Task task : taskList) {
            if (task.taskNumber == taskNumber) {
                return task;
            }
        }
        return null;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        setTaskStatus(true);
        System.out.println("Cool, this task is now done:");
        System.out.format("    [%c] %s\n", this.isTaskDone ? 'X' : ' ', this.taskDescription);
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        setTaskStatus(false);
        System.out.println("Oh okay, this task is now marked as undone:");
        System.out.format("    [%c] %s\n", this.isTaskDone ? 'X' : ' ', this.taskDescription);
    }
}
