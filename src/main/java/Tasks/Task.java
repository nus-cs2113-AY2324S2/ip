package Tasks;

/**
 * The Task class represents a task with content and completion status.
 * It provides methods to access and manipulate task properties.
 */
public abstract class Task {
    private String content;
    private boolean isDone;

    /**
     * Constructs a new Task object with the given content and completion status.
     *
     * @param content The content of the task.
     * @param isDone  The completion status of the task (true if completed, false otherwise).
     */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * Retrieves the content of the task.
     *
     * @return The content of the task.
     */
    public String getContent() {
        return content;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The completion status to set for the task (true if completed, false otherwise).
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, including its completion status and content.
     */
    @Override
    public String toString() {
        String checkmark = isDone ? "X": " ";
        return  " [" + checkmark + "] " + content;
    }
}
