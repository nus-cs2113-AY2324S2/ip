package alpaca.tasks;

/**
 * Abstract representation of a task in the task list，super class of all tasks.
 */
abstract public class Task {
    protected final String description;
    protected boolean isDone;

    protected int IntIsDone; //for saving

    /**
     * Constructs a task with the specified description.
     *
     * @param description The description of the task.
     */
    Task(String description) {
        this.isDone = false;
        this.description = description;
        this.IntIsDone = 0;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
        this.IntIsDone = 1;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmark() {
        this.isDone = false;
        this.IntIsDone = 0;
    }

    /**
     * Returns a string representation of the task, indicating its completion status and description.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        if (isDone) {
            return  "[X] " + description;
        }
        return  "[ ] " + description;
    }

    /**
     * Abstract method to save the task in a format suitable for file storage.
     *
     * @return A string representation of the task for saving.
     */
    abstract public String save();
}