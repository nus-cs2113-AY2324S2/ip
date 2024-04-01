package Tasks;

/**
 * The Task class represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns a formatted string for saving the task to a file.
     *
     * @return A string in file format representing the task.
     */
    public String printFileFormat() {
        return this.getClass().getSimpleName().charAt(0) + " | " + ((isDone)? 1 : 0) + " | " + description;
    }
}