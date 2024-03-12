package fredbot.task;

/**
 * Represents a task that the user wants to add to their task list.
 * A Task contains a description and its done status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Determines the status icon of the task for printing.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Converts the task to a string for printing purposes.
     *
     * @return The status icon and description of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Converts the task to a string for saving purposes.
     *
     * @return The done status and description of the task.
     */
    public String saveString() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
