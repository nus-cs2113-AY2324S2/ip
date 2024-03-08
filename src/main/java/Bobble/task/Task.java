package Bobble.task;

/**
 * The Task class represents a generic task with a description and a status that indicated if the task has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon ("X" if the task is done, " " if the task is not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task.
     *
     * @param isDone The status of the task (true if the task is done, false otherwise).
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
