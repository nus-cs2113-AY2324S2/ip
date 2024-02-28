package misty.data.task;

/**
 * Serves as a template for all tasks.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs Task object - Not used due to class being abstract.
     *
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    /**
     * Returns name of task.
     *
     * @return Name of task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns whether task is marked as done or not as a string.
     *
     * @return Status of task as string.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns whether task is marked as done.
     *
     * @return True if task is marked as done, else false.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets the task as done.
     */
    public void setTaskAsDone() {
        isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setTaskAsNotDone() {
        isDone = false;
    }
}