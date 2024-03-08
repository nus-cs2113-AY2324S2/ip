/**
 * The Task class is an abstract base class representing a task.
 * It provides common properties and methods for tasks.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given description.
     * @param description The task description
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the task description.
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets a string representing the task status icon (done or not done).
     * @return The task status icon string
     */
    String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Sets the task as done or not done.
     * @param done A boolean indicating whether the task is done or not
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a string representation of the task.
     * @return The string representation
     */
    @Override
    public abstract String toString();

    /**
     * Returns a string representation of the task suitable for writing to a file.
     * @return The string representation
     */
    public abstract String toFileString();
}