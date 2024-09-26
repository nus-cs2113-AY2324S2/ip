package geepee.task;

public abstract class Task {

    /** Description of task */
    protected String description;
    /** Completion status of task */
    protected boolean isDone;

    /**
     * Initialises the description and completion status of a task. This constructor
     * is only called by its subclasses.
     * 
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Changes the completion status of the task.
     */
    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("[" + getStatusIcon() + "] " + description);
    }

    /**
     * Returns a string containing details of the task for storage in the data file.
     */
    public String toFileFriendlyString() {
        return String.format(getStatusIcon() + ";" + description);
    }
}
