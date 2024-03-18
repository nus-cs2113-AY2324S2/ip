package drosstasks;

/**
 * Represents a generic task with a description and a completion status .
 */
public class Task {
    private String description;
    private boolean isCompleted;

    /**
     * Constructs a new Task instance.
     *
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false; // Tasks start as not completed by default
    }


    /**
     * Marks the task as completed.
     */
    public void checkTask() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void uncheckTask() {
        this.isCompleted = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The task description.
     */
    public String toString() {
        return this.description;
    }
}
