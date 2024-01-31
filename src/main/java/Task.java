public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }

    public void setIsDone(boolean newStatus) {
        this.isDone = newStatus;
    }

    public boolean isDone() {
        return isDone;
    }
}
