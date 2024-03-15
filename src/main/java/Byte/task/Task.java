package Byte.task;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Gets the status icon of the task.
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
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation of the task for writing to a file.
     *
     * @return A string representation of the task for writing to a file.
     */
    public String toFileString() {
        return "";
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
}
