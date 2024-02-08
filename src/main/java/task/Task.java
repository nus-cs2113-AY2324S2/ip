package task;

public class Task {
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
    public Task(String taskDescription, int taskNumber) {
        setTaskDescription(taskDescription);
        setTaskNumber(taskNumber);
        setTaskStatus(false);
    }

    /**
     * Gets the task description.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public boolean isTaskDone() {
        return isTaskDone;
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
    private void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Sets the status of the associated task.
     *
     * @param isTaskDone Whether the task has been marked completed.
     */
    private void setTaskStatus(boolean isTaskDone) {
        this.isTaskDone = isTaskDone;
    }

    public void printTask() {
        System.out.format("%d.[%c] %s\n",
                getTaskNumber(),
                isTaskDone() ? 'X' : ' ',
                getTaskDescription());
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        setTaskStatus(true);
        System.out.println("Cool, this task is now done:");
        System.out.format("    [%c] %s\n", isTaskDone() ? 'X' : ' ', getTaskDescription());
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        setTaskStatus(false);
        System.out.println("Oh okay, this task is now marked as undone:");
        System.out.format("    [%c] %s\n", isTaskDone() ? 'X' : ' ', getTaskDescription());
    }
}
