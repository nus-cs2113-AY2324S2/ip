package tasks;


/**
 * Stores general information on a task.
 */
public abstract class Task {
    // ATTRIBUTES
    protected String description;
    protected boolean isDone;

    // METHODS
    /**
     * Constructs Task abstract class. Initialise as not done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets isDone.
     */
    public void setDone(boolean state) {
        isDone = state;
    }

    /**
     * Turns isDone into a status icon.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets isDone as an Integer.
     */
    public int getIsDoneInt() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns a String representation of the Task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
