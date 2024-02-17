package geepee.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return String.format("[" + getStatusIcon() + "] " + description);
    }

    public String toFileFriendlyString() {
        return String.format(getStatusIcon() + ";" + description);
    }
}
