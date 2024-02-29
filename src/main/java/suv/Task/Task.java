package suv.Task;

/**
 * The Task class represents a task with a description and completion status.
 * It serves as a base class for different types of tasks such as Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * Retrieves the status icon representing the completion status of the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    /**
     * Marks the task as done.
     */
    public void mark(){
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark(){
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The representation includes the status icon and the description of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
