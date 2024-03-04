/**
 * Represents a generic task in the task list.
 * This class serves as a base class for different types of tasks
 * such as todos, deadlines, and events.
 */
public class Task {
    protected String description; // The task's description
    protected boolean isDone; // The task's completion status

    /**
     * Constructs a new Task with the given description. By default, the task is not done.
     *
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Formats the task for file storage, including its done status and description.
     *
     * @return A string representation of the task suitable for file storage.
     */
    public String toFileFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Sets the done status of the task.
     *
     * @param isDone True if the task is done, false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a status icon indicating whether the task is done.
     *
     * @return A string representing a check mark if done, or a space if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
