package jeff;

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
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Status icon of the task. Returns "X" if the task is done, otherwise returns a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task.
     * Sets the status of the task to done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task.
     * Sets the status of the task to not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String containing the task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task suitable for storing in a file.
     *
     * @return String containing the task's status and description formatted for file storage.
     */
    public String toFileString() {
        return " | " + (isDone ? "1" : "0") + " | " + description.trim();
    }
}
