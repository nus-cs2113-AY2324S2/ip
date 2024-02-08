/**
 * Represents a task with a description and a status.
 */
public class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Creates a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon depending on whether the task is done.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        // Mark done task with X, otherwise a space
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task, including its status and description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
