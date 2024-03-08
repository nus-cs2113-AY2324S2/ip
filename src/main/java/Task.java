/**
 * This represents a task in the application.
 * A task has a description to it and a done status that shows whether is has been completed
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with a description. By default, the task is marked as not done.
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the done status of the task.
     * @return {@code true} if the task is done, otherwise {@code false}
     */
    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, which includes its description and done status.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }
}
