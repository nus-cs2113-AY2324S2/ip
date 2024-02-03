public class Task {
    protected String description;
    protected boolean isDone;
    protected Integer index;

    public Task(String description, boolean b, int i) {
        this.description = description;
        this.isDone = b;
        this.index = i;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
}
