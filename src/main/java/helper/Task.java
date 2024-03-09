package helper;

/**
 * The Task class represents a task with a description and completion status.
 * It serves as a base class for different tasks such as Todo, Deadline, and Event.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done or not.
     *
     * @return The status icon of the task ("[X]" if done, "[ ]" if not done).
     */

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task suitable for file storage.
     * Subclasses should override this method to provide task-specific file representations.
     *
     * @return A string representation of the task for file storage.
     */

    public String toFileString() {
        return null;
    }

    /**
     * Returns a string representation of the task.
     * The representation includes the status icon and the description of the task.
     * Subclasses should override this method to provide task-specific representation.
     *
     * @return A string representation of the task.
     */

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

}
