/**
 * The Task class represents a task with a description and completion status.
 */
public class Task {
    /** The description of the task. */
    protected String description;
    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, otherwise false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The completion status to set.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return description;
    }
}
