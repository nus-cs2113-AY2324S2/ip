package cody.tasks;

/**
 * The Task class represents a general task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task, indicating whether it is completed.
     *
     * @return The status icon ("X" for completed, " " for incomplete).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed or incomplete.
     *
     * @param isDone The completion status to set for the task.
     */
    public void markTask(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the type of the task (e.g., "T" for todo, "D" for deadline, "E" for event).
     *
     * @return The type of the task.
     */
    public String getTaskType() {
        return taskType;
    }
}
