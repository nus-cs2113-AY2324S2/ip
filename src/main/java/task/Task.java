package task;

import java.io.Serializable;

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

    public void setIsDone(boolean newStatus) {
        this.isDone = newStatus;
    }

    public boolean isDone() {
        return this.isDone;
    }
}
