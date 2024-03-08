public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the status icon of the task - X or empty string
     *
     * @return The status icon of the task as a String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the task type of the task
     *
     * @return The task type of the task as a String
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Retrieves the description of the task
     *
     * @return The description of the task as a String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, indicating its status and description.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
