public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = null;

    public Task() {
        this("[Add Description Here]");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType(), this.getStatusIcon(), this.description);
    }
}
