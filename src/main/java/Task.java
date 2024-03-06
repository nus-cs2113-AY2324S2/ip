/**
 * Task class with methods for handling tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates task and sets to being undone initially
     *
     * @param description name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return symbol for if task is marked or unmarked based on isDone boolean
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //mark done task with X
    }

    /**
     *  Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark a task to be undone
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return description;
    }
}
