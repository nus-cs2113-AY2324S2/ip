package ip.task;

/**
 * Represents a task, containing the description of the task
 * and whether the task has been marked as completed
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise the object's description as the description argument
     * and the object's isDone as false
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is completed and returns " " otherwise
     *
     * @return a character representing whether the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a formatted String containing the status icon
     * and the description of the task
     *
     * @return a String specifying if the task is done and its description
     */
    public String getDetails() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
