package tasks;

/**
 * The base class of deadline, event and todo.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The string showing the completion status of the task.
     *
     * @return X when the task is completed, one blank space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the description of a task.
     *
     * @return The description of a task.
     */
    public String getDescription() {
        return this.description;
    }
}
