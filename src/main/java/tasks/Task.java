package tasks;

/**
 * Represents a task that has been completed or to be completed.
 * This is an abstract class which serves as a template for the 3 different types of tasks - ToDo, Event, Deadline.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public abstract String getTaskType();
    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Retrieves the status icon for the task.
     * @return The status icon ('X' if the task is done, ' ' if it's not done)
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Converts the task into its string format.
     * @return The task in string format
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
