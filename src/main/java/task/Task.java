package carrot.task;

/**
 * This is a the base superclass for other task formats, such as Todo,Event,Deadline
 * <p>
 * Each task has a description indicating the task's details and a completion status
 * indicating whether the task has been marked as done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone      the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     * <p>
     * The status icon is "X" if the task is marked as done, otherwise it is a space (blank).
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the completion status of the task.
     *
     * @param mark the completion status to set for the task
     */
    public void setStatus(boolean mark) {
        isDone = mark;
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * The String format is ["statusIcon"] "description"
     *
     * @return a string representation of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the task in a format suitable for storing in a file.
     * <p>
     * The task is formatted with the completion status and the description enclosed in double quotes.
     * Any double quotes in the description are escaped to prevent interference with the file format.
     *
     * @return the task in file format
     */
    public String toFileFormat() {
        // Escape characters: usertyped " would not interfere with file format
        String escapedDescription = description.replace("\"", "\\\"");

        return " | " + isDone + " | \"" + description + "\"";
    }
}
