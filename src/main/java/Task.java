public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDetails() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    public void setDone(boolean newStatus) {
        isDone = newStatus;
    }
}
