public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public abstract String toString();

    public abstract String toFileString();
}