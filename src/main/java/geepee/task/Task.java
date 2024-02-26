package geepee.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("[" + getStatusIcon() + "] " + description);
    }

    public String toFileFriendlyString() {
        return String.format(getStatusIcon() + ";" + description);
    }
}
