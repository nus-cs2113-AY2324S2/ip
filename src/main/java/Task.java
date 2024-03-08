/**
 * The Task class represents a task with a description and completion status.
 */
public class Task {
    protected String description; // Description of the task
    protected boolean isDone;     // Completion status of the task

    /**
     * Constructs a Task object with the given description.
     * By default, the task is marked as not done.
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the status icon of the task.
     * @return The status icon, [X] if done, [ ] if not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the task.
     * Includes the status icon and description.
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
