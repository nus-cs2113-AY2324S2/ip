package alpaca.tasks;

/**
 * Represents a task in the task list
 */
abstract public class Task {
    protected final String description;
    protected boolean isDone;

    protected int IntIsDone; //for saving

    /**
     * Constructor for a task
     * @param description the description of the task
     */
    Task(String description) {
        this.isDone = false;
        this.description = description;
        this.IntIsDone = 0;
    }

    /**
     * Get the description of the task
     * @return the description of the task
     */
    public void mark() {
        this.isDone = true;
        this.IntIsDone = 1;
    }

    /**
     * Get the description of the task
     * @return the description of the task
     */
    public void unmark() {
        this.isDone = false;
        this.IntIsDone = 0;
    }

    /**
     * Get the description of the task
     * @return the description of the task
     */
    public String toString() {
        if (isDone) {
            return  "[X] " + description;
        }
        return  "[ ] " + description;
    }

    abstract public String save();
}