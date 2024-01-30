public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        setDone(false);
    }
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setDone(boolean done) {
        isDone = done;
    }
}
