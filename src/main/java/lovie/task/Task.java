package lovie.task;

/**
 * Represents a task.
 */
public class Task {
    public static final String PLACEHOLDER = "";
    public static final String COMPLETED_MARK = "✓";
    public static final String UNCOMPLETED_MARK = " ";
    protected String description;
    protected boolean isDone;


    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return this.description + " | " + (isDone ? "1" : "0");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? COMPLETED_MARK : UNCOMPLETED_MARK);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the icon of the task.
     *
     * @return The icon of the task.
     */
    public String getTaskIcon() {
        return PLACEHOLDER;
    }

    /**
     * Returns the timespan of the task.
     *
     * @return The timespan of the task.
     */
    public String getTimespan() {
        return PLACEHOLDER;
    }
}
