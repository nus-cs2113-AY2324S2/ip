/**
 * Represents a generic task in Phoebe.
 * This class serves as a base class for different types of tasks.
 */
public class Task {
    protected String description; // Description of the task
    protected boolean isDone;     // Task completion status
    protected String type;        // Type of the task (for extended classes)

    /**
     * Constructs a new Task with the specified description.
     * The task is not completed by default.
     *
     * @param description the description of the task.
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
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates the task is completed, and " " (space) indicates it is not.
     *
     * @return the status icon as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task, including its status icon and description.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task into a format suitable for file storage.
     * The format includes the task type, completion status, and description.
     *
     * @return a string representation of the task in a file storage format.
     */
    public String toFileFormat() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description.
     */
    public String getDescription() {
        return description;
    }
}

