/**
 * Represents a basic task in the Jane task management application.
 * It provides methods to get and set the task status, retrieve the task description,
 * and generate a formatted string representation of the task.
 */
public class Task {
    /** Description of the task. */
    protected String description;

    /** Status of the task (done or not done). */
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the status icon ("[X]" for done, "[ ]" for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task (done or not done).
     *
     * @param status The status to be set for the task.
     */
    public void setDone(Boolean status) {
        this.isDone = status;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Generates a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}