package natsu.task;

/**
 * Represents a generic task in a task management application.
 * A task is characterized by a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task instance with the specified description.
     * By default, a new task is not completed.
     *
     * @param description The descriptive text of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the task's completion status.
     * A completed task is marked with "[X]", and an incomplete task with "[ ]".
     *
     * @return A string representing the completion status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Marks the task as completed by setting its completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed by setting its completion status to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string that combines the task's completion status icon and its description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
