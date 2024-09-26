/**
 * Abstract class representing a generic task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for creating a new Task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns the description of the task. */
    public String getDescription() {
        return description;
    }

    /** Returns the status icon indicating whether the task is done. */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }


    /** Marks the task as done. */
    public void markAsDone(){
        this.isDone = true;
    }

    /** Marks the task as not done. */
    public void markAsUndone(){
        this.isDone = false;
    }

    /** Returns the task in a format suitable for saving to a file. */
    public abstract String toSaveFormat();

    /** Returns a string representation of the task. */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description; // Return status icon and description
    }
}
