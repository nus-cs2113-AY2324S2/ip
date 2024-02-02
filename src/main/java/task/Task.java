package task;

import java.util.ArrayList;

public class Task {
    private static final int MAX_TASKS = 128;
    private static int totalTasks = 0;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    private String taskDescription;
    private int taskNumber;

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
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Give the newly created task an associated task number.
     */
    public void setTaskNumber() {
        this.taskNumber = totalTasks + 1;
    }

    /**
     * Prints out the whole list of tasks that have been added so far.
     */
    public static void printTaskList() {
        for (Task t : taskList) {
            System.out.format("%d. %s\n", t.taskNumber, t.taskDescription);
        }
    }
}
