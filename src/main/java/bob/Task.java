package bob;

/**
 * Represents a task in the list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * @param description The description of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the Task class.
     * @param stringRepresentation The string representation of the task.
     */
    public Task(String stringRepresentation) {
        this.isDone = stringRepresentation.substring(0, 6).contains("X");
        String[] split = stringRepresentation.substring(7).split(" ");
        this.description = split[0];
    }

    /**
     * Returns the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    public String getListItem() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Sets the task as done.
     * @param done The status of the task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    public String toString() {
        return Ui.TODO_ICON + getListItem();
    }
}
