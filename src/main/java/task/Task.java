package task;

import java.io.Serializable;

/*
 * Abstract class for tasks.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Sets the status of the task.
     *
     * @param newStatus New status of the task.
     */
    public void setIsDone(boolean newStatus) {
        this.isDone = newStatus;
    }

    /**
     * Returns whether the task is marked as done.
     * 
     * @return True if the task is marked as done, false otherwise. 
     */
    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}
