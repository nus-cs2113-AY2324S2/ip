package blue.task;

/**
 * Represents a general task.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task() {
        this("");
    }
    /**
     * Overloaded constructor that creates an undone task.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns an "X" symbol if task is done, " " otherwise.
     *
     * @return A string symbol indicating the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of a task in text-parsable format.
     *
     * @return A string of this task in text-parsable format.
     */
    public String toSaveTextFormat() {
        String done = (getStatusIcon().equals("X") ? "1" : "0");
        return "T|" + done + "|" + description + System.lineSeparator();
    }

    @Override
    public String toString() {
        return this.getDescription() + " [" + this.getStatusIcon() + "]";
    }
}
