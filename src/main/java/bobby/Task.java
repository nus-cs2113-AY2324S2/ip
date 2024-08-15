package bobby;

/**
 * Represents a task.
 */
public class Task {
    /** The description of the task. */
    protected String description;
    /** Indicates whether the task is done or not. */
    protected boolean isDone;

    /**
     * Constructs a new Task instance with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Indicates whether the task is done or not.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the "by" attribute of the task.
     *
     * @return The "by" attribute of the task.
     */
    public String getBy() {
        return "";
    }

    /**
     * Gets the "from" attribute of the task.
     *
     * @return The "from" attribute of the task.
     */
    public String getFrom() {
        return "";
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone Indicates whether the task is done or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Prints the description of the task.
     */
    public void printDescription() {
        System.out.println(description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, including its status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
