package schmidt.task;

/**
 * Represents a task with a description and a boolean to indicate if it is done.
 */
public class Task {
    public static final String DONE = "1";
    public static final String NOT_DONE = "0";
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON); // mark done task with X
    }

    /**
     * This method marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * This method returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method returns the status of the task.
     *
     * @return The status of the task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * This method returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
