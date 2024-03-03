package ava.task;

/**
 * Represents a task.
 * Its field "description" is the name of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isDone = isCompleted;
    }

    /**
     * Converts a Task object to a string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon()
                + "] " + this.getDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}
