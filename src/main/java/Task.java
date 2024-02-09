public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markList() {
        this.isDone = true;
    }

    public void unmarkList() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}
