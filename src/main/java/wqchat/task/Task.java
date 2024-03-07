package wqchat.task;

/**
 * Represents a task that has a description.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public int getIsDone() {
        return isDone ? 1 : 0;
    }
    public abstract String getType();
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
