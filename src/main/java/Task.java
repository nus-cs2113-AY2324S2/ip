public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDone(Boolean status) {
        this.isDone = status;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}

