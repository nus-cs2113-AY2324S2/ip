public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return (isDone ? ".[X] " : ".[ ] ") + this.description; // mark done task with X
    }

    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }

}
