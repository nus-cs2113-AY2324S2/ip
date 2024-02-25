package quill.task;

/**
 * The Task Class represents a class in the Quill application.
 * It serves as the base class for different types of tasks
 * and provides common task attributes and methods.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description
     * and sets its initial completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the string representation of the task's completion status.
     *
     * @return "[X] "if the task is done, "[ ] " if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Get description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the completion status of the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the completion status of the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for printing.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Returns a string representation of the task for storing.
     *
     * @return A string representation of the task.
     */
    public String saveTask() {
        return isDone + " | " + description;
    }
    
}
