package duke.task;

/**
 * Represents the general Task of the Duke chatbot.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the specified description and whether it is complete.
     *
     * @param description Description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of whether the Task is completed.
     *
     * @return String representing the status of the Task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as not completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the Task to the specific format for saving to the text file.
     *
     * @return A formatted string containing the Task for saving.
     */
    public String toDisk() {
        return " | " + (this.isDone ? "1 | " : "0 | ") + this.description;
    }

    /**
     * Converts the Task to the specific format for displaying on the screen.
     *
     * @return A formatted string containing the Task for display.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
