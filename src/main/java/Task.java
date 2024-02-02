/**
 * Task class represents a task with a description and a boolean to indicate if it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method returns the status icon of the task
     * @return The status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method unmarks the task as done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * This method returns the description of the task
     * @return The description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method returns the string representation of the task
     * @return The string representation of the task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
