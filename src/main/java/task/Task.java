package task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    protected static final String DONE = "X";
    protected static final String IN_PROGRESS = " ";
    protected boolean isDone;
    protected final String description;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean containsWord(String keyword) {
        return description.contains(keyword);
    }

    public boolean containsTime(String keyword) {
        return false;
    }

    @Override
    public String toString() {
        String status = isDone ? DONE : IN_PROGRESS;
        return "[" + status + "] " + description;
    }
}
